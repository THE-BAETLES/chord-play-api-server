package com.chordplay.chordplayapiserver.infra.messageQueue.sqs;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MessageSenderWithTemplateTest {


    @Autowired
    SqsMessageSender messageSenderWithTemplate;

    @Test
    @Transactional
    @DisplayName("Test message fifo queue")
    @Rollback(value = false)
    public void test1() {
        messageSenderWithTemplate.sendToFifoQueue("testtest","4","test");
    }
}