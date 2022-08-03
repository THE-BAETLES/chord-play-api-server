package com.chordplay.chordplayapiserver.global.sse.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.chordplay.chordplayapiserver.global.sse.dao.SseEmitterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.connection.Message;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{

    private static final Long DEFAULT_TIMEOUT = 60L * 1000 * 5;
    private final SseEmitterRepository sseEmitterRepository;

    public SseEmitter subscribe(String userId, String videoId, String lastEventId) {
        CustomSseEmitter customSseEmitter = CustomSseEmitter.builder().videoId(videoId).userId(userId).timeout(DEFAULT_TIMEOUT).build();
        sseEmitterRepository.save(customSseEmitter);

        customSseEmitter.onCompletion(() -> sseEmitterRepository.deleteById(customSseEmitter.getId()));
        customSseEmitter.onTimeout(() -> sseEmitterRepository.deleteById(customSseEmitter.getId()));
        customSseEmitter.onError((e) -> {
            sseEmitterRepository.deleteById(customSseEmitter.getId());
            throw new RuntimeException(e); //예외처리 추가
        });
        sendToClient(customSseEmitter, "EventStream Created. [userId=" + userId + "]");
        return customSseEmitter;
    }

    public void sendStatusToClient(AiStatusMessage aiStatusMessage){

        String videoId = aiStatusMessage.getVideoId();

        List<CustomSseEmitter> customSseEmitters = sseEmitterRepository.findAllByVideoId(videoId);

        customSseEmitters.stream().forEach(emitter -> {
            sendToClient(emitter, aiStatusMessage);
        });
        //해당 사람들에게 status 보내기

    }

    private void sendToClient(CustomSseEmitter emitter, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(emitter.getId())
                    .name("sse")
                    .data(data));
        } catch (IOException exception) {
            sseEmitterRepository.deleteById(emitter.getId());
            throw new RuntimeException("연결 오류!"); // 예외처리 추가
        }
    }
}
