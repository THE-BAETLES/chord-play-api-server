package com.chordplay.chordplayapiserver.global.sse.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import com.chordplay.chordplayapiserver.global.sse.CustomSseEmitter;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter subscribe(String userId, String videoId, String lastEventId);
    void sendStatusToClient(AiStatusMessage aiStatusMessage, CustomSseEmitter emitter);
    void sendToClient(CustomSseEmitter emitter, Object data);
    void publish(AiStatusMessage aiStatusMessage) throws JsonProcessingException;
}
