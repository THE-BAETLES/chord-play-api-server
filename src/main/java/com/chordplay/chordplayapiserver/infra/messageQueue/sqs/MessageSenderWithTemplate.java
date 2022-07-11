package com.chordplay.chordplayapiserver.infra.messageQueue.sqs;

import com.amazonaws.services.sqs.AmazonSQSAsync;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageSenderWithTemplate {
    private final QueueMessagingTemplate queueMessagingTemplate;

    public void send(String message) {
        this.queueMessagingTemplate.send("physicalQueueName", MessageBuilder.withPayload(message).build());
    }

}
