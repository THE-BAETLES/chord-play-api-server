package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.dao.SheetRepository;
import com.chordplay.chordplayapiserver.domain.dao.UserRepository;
import com.chordplay.chordplayapiserver.domain.dao.WatchHistoryRepository;
import com.chordplay.chordplayapiserver.domain.entity.*;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.domain.sheet.exception.SheetDataNotFoundException;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.chordplay.chordplayapiserver.domain.sheet.listener.SheetRedisListener;
import com.chordplay.chordplayapiserver.infra.messageQueue.MessageQueue;
import com.chordplay.chordplayapiserver.infra.redis.RedisUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
@Slf4j
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;
    private final SheetRepository sheetRepository;
    private final NotificationService notificationService;
    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;
    private final RedisMessageListenerContainer redisMessageListenerContainer;
    private final RedisUtil redisUtil;
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
    public SseEmitter createSheet(SheetAiRequest req) {

        CustomSseEmitter sseEmitter = notificationService.subscribe("request_user_id", req.getVideoId());
        Optional<Sheet> sheet = sheetRepository.findOneByVideoId(req.getVideoId());
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
            Video video = new Video(req.getVideoId());
            User adminUser = userRepository.findByUsername("Chord Play");
            sheetRepository.save(Sheet.builder()
                    .video(video) //임시
                    .user(adminUser)
                    .title("ai")
                    .build());
        }
        alertSheetCreationProgress(sseEmitter,req.getVideoId());
        return sseEmitter;
    }

    @Override
    public SheetData getSheetData(String sheetId) {

        Optional<SheetData> sheetData = sheetDataRepository.findOneById(sheetId);
        if (sheetData.isPresent()) {
            return sheetData.get();
        } else {
            throw new SheetDataNotFoundException();
        }
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
            throw new RuntimeException("emitter error");    // 예외처리
        });

    }

    private void saveHistory(){
//        //user, video, lastPlayed
//        WatchHistory watchHistory =
//        watchHistoryRepository.save();
//
//        //
//
//        //update

    }
}
