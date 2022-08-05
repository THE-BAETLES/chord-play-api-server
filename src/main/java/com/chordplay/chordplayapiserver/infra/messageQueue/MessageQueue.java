package com.chordplay.chordplayapiserver.infra.messageQueue;

public interface MessageQueue {
    void sendToFifoQueue(Object messagePayload, String messageGroupID, String messageDedupID);
}
