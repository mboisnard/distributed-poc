package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.stack.StackMicroservices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ConsumerService {
    StackMicroservices stackMicroservices;

    public ConsumerService() {
        this.stackMicroservices = new StackMicroservices();
    }

    @KafkaListener(topics = "${kafka.topic.name}")
    public void consumeMessage(final Metrics metrics) {
        stackMicroservices.push(metrics);
        stackMicroservices.getMicroservices().forEach((k, v) -> {
            log.info("Leader elected for " + k + ": " + v.getLeader());
        });
    }
}
