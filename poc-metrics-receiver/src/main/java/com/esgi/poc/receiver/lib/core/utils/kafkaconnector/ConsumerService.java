package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ConsumerService {

    @KafkaListener(topics = "${kafka.topic.name}")
    public void consumeMessage(final Metrics metrics) {
        log.info(metrics.toString());
    }
}
