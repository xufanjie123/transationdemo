package com.fanjiexu.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "rocketmq.producer")
@Configuration
@ToString
@Data
public class ProducerConfig {

    private String namesrvAddr;

    private String groupName;

}
