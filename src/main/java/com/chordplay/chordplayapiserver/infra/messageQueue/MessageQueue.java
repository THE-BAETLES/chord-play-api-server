package com.chordplay.chordplayapiserver.infra.messageQueue;

public interface MessageQueue {
    void sendToFifoQueue(String messagePayload, String messageGroupID, String messageDedupID);
}
