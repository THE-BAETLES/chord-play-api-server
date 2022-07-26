package com.chordplay.chordplayapiserver.infra.messageQueue.sqs;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "cloud.aws.sqs.queue")
public class AmazonSQSProperties {
    private String name;
    private String url;
}
