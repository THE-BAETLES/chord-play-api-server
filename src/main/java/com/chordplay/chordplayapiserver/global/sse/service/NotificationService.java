package com.chordplay.chordplayapiserver.global.sse.service;

import com.chordplay.chordplayapiserver.domain.sheet.dto.AiStatusMessage;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

public interface NotificationService {
    SseEmitter subscribe(String userId, String videoId, String lastEventId);
    void sendStatusToClient(AiStatusMessage aiStatusMessage);
}
