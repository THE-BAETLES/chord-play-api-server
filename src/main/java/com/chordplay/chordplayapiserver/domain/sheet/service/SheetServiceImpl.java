package com.chordplay.chordplayapiserver.domain.sheet.service;

import com.chordplay.chordplayapiserver.domain.entity.SheetData;
import com.chordplay.chordplayapiserver.domain.dao.SheetDataRepository;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetAiRequest;
import com.chordplay.chordplayapiserver.domain.sheet.dto.SheetDataResponse;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.service.NotificationService;
import com.chordplay.chordplayapiserver.global.util.ContextUtil;
import com.chordplay.chordplayapiserver.domain.sheet.SheetRedisListener;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;


@RequiredArgsConstructor
@Service
public class SheetServiceImpl implements SheetService{

    private final SheetDataRepository sheetDataRepository;
    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;
    private final RedisMessageListenerContainer redisMessageListenerContainer;

    @Override
    public SseEmitter createAi(SheetAiRequest dto) {
        String userId = ContextUtil.getPrincipalUserId();
        SseEmitter sseEmitter = notificationService.subscribe(userId, dto.getVideo_id(), "temp");
        //mongo check -> 있다? return sheet


        //redis check -> status가 없다?
            //listener 등록
            //sqs message
        //있다?
            //listener 등록

        return sseEmitter;
    }

    public SheetDataResponse read(String id){

        SheetData sheetData = sheetDataRepository.findById(id).get();
        return new SheetDataResponse(sheetData);
    }
    private void addChannel(CustomSseEmitter emitter){

        MessageListener messageListener = new MessageListenerAdapter(SheetRedisListener.builder()
                .emitter(emitter)
                .notificationService(notificationService)
                .objectMapper(objectMapper)
                .build());

        redisMessageListenerContainer.addMessageListener(messageListener, new ChannelTopic("video_id"));

        emitter.onCompletion(()->redisMessageListenerContainer.removeMessageListener(messageListener));
        emitter.onTimeout(()->redisMessageListenerContainer.removeMessageListener(messageListener));
        emitter.onError((e)->{
            redisMessageListenerContainer.removeMessageListener(messageListener);
            throw new RuntimeException("emitter error");    // 예외처리
        });

    }

}
