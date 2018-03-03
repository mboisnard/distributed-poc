package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.stack.StackMicroservices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class ConsumerService {

    private final StackMicroservices stackMicroservices;

    ConsumerService(final StackMicroservices stackMicroservices) {
        this.stackMicroservices = stackMicroservices;
    }

    @KafkaListener(topics = "${kafka.topic.name}")
    public void consumeMessage(final Metrics metrics) {

        stackMicroservices.push(metrics);
        stackMicroservices.getMicroservices().forEach((microserviceId, stackMetrics) -> {
            log.info("Leader elected for " + microserviceId + ": " + stackMetrics.getLeader());
        });
    }
}
