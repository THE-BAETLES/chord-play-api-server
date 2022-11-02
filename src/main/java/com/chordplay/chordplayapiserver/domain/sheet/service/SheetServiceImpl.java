package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.dao.*;
import com.chordplay.chordplayapiserver.domain.entity.*;
import com.chordplay.chordplayapiserver.domain.sheet.dto.*;
import com.chordplay.chordplayapiserver.domain.sheet.exception.AiSheetNotCreatedException;
import com.chordplay.chordplayapiserver.domain.user.exception.UserNotFoundException;
import com.chordplay.chordplayapiserver.global.exception.ForbiddenException;
import com.chordplay.chordplayapiserver.global.exception.UnauthorizedException;
import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetDataNotFoundException;
import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetNotFoundException;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.chordplay.chordplayapiserver.domain.sheet.listener.SheetRedisListener;
import com.chordplay.chordplayapiserver.infra.messageQueue.MessageQueue;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;
    private final SheetRepository sheetRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final SheetLikeRepository sheetLikeRepository;
    private final ObjectMapper objectMapper;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final MessageQueue messageQueue;

    private final WatchHistoryRepository watchHistoryRepository;

    private final String CREATE_AI_SHEET = "CREATE_AI_SHEET";

    private final String adminUserName = "Chord Play";
    private final String aiSheetTitle = "Chord Play";

    private void alertSheetCreationProgress(CustomSseEmitter sseEmitter, String videoId) {
        addRedisListener(sseEmitter); //ai서버 레디스 리스터를 sse
        messageQueue.sendToFifoQueue(videoId,CREATE_AI_SHEET, videoId);
    }

    /**
     * Sheet을 생성합니다
     *
     * for important notes on exception handling.
     * @param object the object to write
     * @throws IOException raised when an I/O error occurs
     * @throws java.lang.IllegalStateException wraps any other errors
     */
    @Override
    public SseEmitter createSheetProcess(String videoId) {

        CustomSseEmitter sseEmitter = notificationService.subscribe("request_user_id", videoId);
        Optional<Sheet> sheet = sheetRepository.findFirstByVideoId(videoId);
        boolean sheetRequestExists = sheet.isPresent();

        if(sheetRequestExists == false) createOnlySheet(videoId);

        Optional<SheetData> sheetData = sheetDataRepository.findOneById(sheet.get().getId());
        if (sheetData.isPresent()) {
            log.info("Already exist sheetData: " + sheetData.get().getId());
            //notificationService.sendToClient(sseEmitter, new SheetDataResponse(sheetData.get()));
            notificationService.sendToClient(sseEmitter, 3);
            sseEmitter.complete();
            return sseEmitter;
        }

        alertSheetCreationProgress(sseEmitter,videoId);
        return sseEmitter;
    }

    @Override
    public SheetData getSheetData(String sheetId) {
        SheetData sheetData = null;
        Optional<SheetData> sheetDataOptional = sheetDataRepository.findOneById(sheetId);
        sheetData = sheetDataOptional.orElseThrow(() -> new SheetDataNotFoundException());
        updateWatchHistory(sheetId);
        return sheetData;
    }

    @Override
    public Sheet getSheet(String sheetId) {
        return sheetRepository.findById(sheetId).orElseThrow(() -> new SheetNotFoundException());
    }



    @Override
    public Sheet deleteSheetAndSheetData(String sheetId) {
        Sheet sheet = sheetRepository.findById(sheetId).orElseThrow(() -> new SheetNotFoundException());
        if (!sheet.getUser().getId().equals(ContextUtil.getPrincipalUserId())) throw new UnauthorizedException();

        sheetRepository.delete(sheet);
        sheetDataRepository.findById(sheetId).ifPresent(sheetData->{
            sheetDataRepository.delete(sheetData);
        });
        return sheet;
    }

    @Override
    public Sheet createOnlySheet(String videoId) {
        Video video = new Video(videoId);
        User adminUser = userRepository.findByUsername(adminUserName);
        Sheet sheet = sheetRepository.save(Sheet.builder()
                .video(video) //임시
                .user(adminUser)
                .title(aiSheetTitle)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
        return sheet;
    }

    @Override
    public SheetsResponse getSheetsByVideoId(String videoId) {

        User user = userRepository.findById(ContextUtil.getPrincipalUserId()).orElseThrow(()-> new UserNotFoundException());
        List<Sheet> sharedSheets = sheetRepository.findAllByVideoId(videoId);

        List<SheetResponse> sharedSheetResponses = new ArrayList<>();
        List<SheetResponse> likeSheetResponses = new ArrayList<>();
        List<SheetResponse> mySheetResponses = new ArrayList<>();

        for(Sheet sheet: sharedSheets){
            SheetResponse sheetResponse = toSheetResponse(sheet,user);
            sharedSheetResponses.add(sheetResponse);

            if (sheetResponse.getLiked()){
                likeSheetResponses.add(sheetResponse);
            }
            if (sheet.getUser().equals(user)){
                mySheetResponses.add(sheetResponse);
            }
        }
       return new SheetsResponse(sharedSheetResponses,likeSheetResponses,mySheetResponses);
    }

    @Override
    public List<Sheet> getSharedSheets(String videoId) {
        List<Sheet> sheets = sheetRepository.findAllByVideoId(videoId);
        return sheets;
    }

    @Override
    public void updateSheetChord(String sheetId, SheetChangeRequest dto) {
        Sheet sheet = sheetRepository.findById(sheetId).orElseThrow(() -> new SheetNotFoundException());
        if (!sheet.getUser().getId().equals(ContextUtil.getPrincipalUserId()))
            throw new ForbiddenException();
        sheetDataRepository.updateSheetChord(sheetId,dto.getPosition(),dto.getChord());
    }

    private void updateWatchHistory(String sheetId){
        String userId = ContextUtil.getPrincipalUserId();
        String videoId = sheetRepository.findById(sheetId)
                .orElseThrow(()-> new SheetNotFoundException())
                .getVideo().getId();
        watchHistoryRepository.updateCountAndTimeByUserAndVideo(userId, videoId);
    }

    private void addRedisListener(CustomSseEmitter emitter){

        MessageListener messageListener = new MessageListenerAdapter(SheetRedisListener.builder()
                .emitter(emitter)
                .notificationService(notificationService)
                .objectMapper(objectMapper)
                .build());
        log.info("add message listener ! channel: " + emitter.getVideoId());
        redisMessageListenerContainer.addMessageListener(messageListener, new ChannelTopic(emitter.getVideoId()));

        emitter.onCompletion(()->redisMessageListenerContainer.removeMessageListener(messageListener));
        emitter.onTimeout(()->redisMessageListenerContainer.removeMessageListener(messageListener));
        emitter.onError((e)->{
            redisMessageListenerContainer.removeMessageListener(messageListener);
            log.error("emitter error");
            throw new RuntimeException("emitter error");    // 예외처리
        });

    }

    @Override
    public Sheet duplicateSheet(SheetDuplicationRequest dto) {

        Sheet sheet = sheetRepository.findById(dto.getSheetId()).orElseThrow(() -> new SheetNotFoundException());
        Sheet finalSheet = sheet;
        SheetData sheetData = sheetDataRepository.findById(dto.getSheetId()).orElseThrow(() -> {
            if ( finalSheet.getUser().getUsername().equals(adminUserName)){
                return new AiSheetNotCreatedException();
            }
            return new SheetDataNotFoundException();
        });
        User user = new User(ContextUtil.getPrincipalUserId());

        Sheet newSheet = Sheet.builder()
                .video(sheet.getVideo())
                .user(user)
                .title(dto.getTitle())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        sheet = sheetRepository.save(newSheet);

        SheetData newSheetData = SheetData.builder()
                .id(sheet.getId())
                .bpm(sheetData.getBpm())
                .chordInfos(sheetData.getChordInfos())
                .build();

        sheetDataRepository.save(newSheetData);

        return sheet;
    }

    protected SheetResponse toSheetResponse(Sheet sheet, User user){
        SheetResponse sheetResponse = new SheetResponse(sheet);
        Optional<SheetLike> sheetLikeOptional = sheetLikeRepository.findBySheetAndUser(sheet,user);
        if (sheetLikeOptional.isPresent()){
            sheetResponse.setLiked(true);
        }
        sheetResponse.setLikeCount(sheetRepository.getSheetLikeCount(sheet.getId()));

        sheetResponse.setNickname(user.getNickname());
        return sheetResponse;
    }

    @Override
    public List<SheetResponse> getSheetsOfMyLike() {

        List<SheetResponse> sheetResponses = new ArrayList<>();
        User user = userRepository.findById(ContextUtil.getPrincipalUserId()).orElseThrow(()-> new UserNotFoundException());
        List<SheetLike> sheetLikes = sheetLikeRepository.findAllByUser(user);
        sheetLikes.forEach(sheetLike -> {
            Sheet sheet = sheetLike.getSheet();
            sheetResponses.add(toSheetResponse(sheet,user));
        });
        return sheetResponses;
    }

    @Override
    public List<SheetResponse> getMySheets() {
        List<SheetResponse> sheetResponses = new ArrayList<>();
        User user = userRepository.findById(ContextUtil.getPrincipalUserId()).orElseThrow(()-> new UserNotFoundException());
        List<Sheet> sheets = sheetRepository.findAllByUser(user);

        for (Sheet sheet: sheets) {
            sheetResponses.add(toSheetResponse(sheet,user));
        }
        return sheetResponses;
    }
}
