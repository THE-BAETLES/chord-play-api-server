package com.chordplay.chordplayapiserver.infra.messageQueue.sqs;

import com.chordplay.chordplayapiserver.infra.messageQueue.MessageQueue;
import io.awspring.cloud.messaging.core.QueueMessagingTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SqsMessageSender implements MessageQueue {

    private final QueueMessagingTemplate queueMessagingTemplate;
    private final AmazonSQSProperties amazonSQSProperties;

    @Override
    public void sendToFifoQueue(Object messagePayload, String messageGroupID, String messageDedupID) {

        Message msg = MessageBuilder.withPayload(messagePayload)
                .setHeader("message-group-id", messageGroupID)
                .setHeader("message-deduplication-id", messageDedupID)
                .build();
        queueMessagingTemplate.send(amazonSQSProperties.getName(), msg);
        log.info("message(message-group-id: "+messageGroupID+", deduplication-id: "+messageDedupID+")sent to " + amazonSQSProperties.getName());
    }

}
