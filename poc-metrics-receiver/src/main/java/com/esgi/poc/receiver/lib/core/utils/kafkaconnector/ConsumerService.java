package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentLogging;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ConsumerService {
    private final CountDownLatch latch;

    @Autowired
    ConsumerService() {
        this.latch = new CountDownLatch(1);
    }

    @KafkaListener(topics = "${topic.name}")
    public void consumeMessage(Metrics m) {
        System.out.println(m.toString());
    }
}
