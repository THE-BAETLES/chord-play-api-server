package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.dao.WatchHistoryRepository;
import com.chordplay.chordplayapiserver.domain.entity.*;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
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
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;
    private final SheetRepository sheetRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final MessageQueue messageQueue;

    private final WatchHistoryRepository watchHistoryRepository;

    private final String CREATE_AI_SHEET = "CREATE_AI_SHEET";

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
            Optional<Sheet> sheet = sheetRepository.findOneByVideoId(videoId);
            boolean sheetRequestExists = sheet.isPresent();
            if (sheetRequestExists) {
                Optional<SheetData> sheetData = sheetDataRepository.findOneById(sheet.get().getId());
                if (sheetData.isPresent()) {
                    log.info("Already exist sheetData: " + sheetData.get().getId());
                    notificationService.sendToClient(sseEmitter, new SheetDataResponse(sheetData.get()));
                    sseEmitter.complete();
                    return sseEmitter;
                }
            } else {
                createOnlySheet(videoId);
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
        if (sheet.getUser().getId() != ContextUtil.getPrincipalUserId()) throw new UnauthorizedException();

        sheetRepository.delete(sheet);
        sheetDataRepository.findById(sheetId).ifPresent(sheetData->{
            sheetDataRepository.delete(sheetData);
        });
        return sheet;
    }

    @Override
    public Sheet createOnlySheet(String videoId) {
        Video video = new Video(videoId);
        User adminUser = userRepository.findByUsername("Chord Play");
        Sheet sheet = sheetRepository.save(Sheet.builder()
                .video(video) //임시
                .user(adminUser)
                .title("ai")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build());
        return sheet;
    }

    private void updateWatchHistory(String sheetId){
        Optional<Sheet> sheetOptional = sheetRepository.findById(sheetId);
        Sheet sheet = sheetOptional.orElseThrow(() -> new SheetNotFoundException());
        watchHistoryRepository.updateCountAndTimeByUserAndVideo(sheet.getUser(), sheet.getVideo());
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
}
