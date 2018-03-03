package com.esgi.poc.metrics.sender;

import com.esgi.poc.metrics.Metrics;
import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.endpoint.HealthEndpoint;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.boot.actuate.health.Health;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
class MetricsSenderServiceImpl implements MetricsSenderService {

    private final MetricsEndpoint metricsEndpoint;

    private final HealthEndpoint healthEndpoint;

    private final EurekaClient eurekaClient;

    private final KafkaTemplate<String, Metrics> kafkaTemplate;

    private final String kafkaTopic;

    MetricsSenderServiceImpl(final MetricsEndpoint metricsEndpoint,
                             final HealthEndpoint healthEndpoint,
                             final EurekaClient eurekaClient,
                             final KafkaTemplate<String, Metrics> kafkaTemplate,
                             @Value("${kafka.topic.metrics}") final String kafkaTopic) {
        this.metricsEndpoint = metricsEndpoint;
        this.healthEndpoint = healthEndpoint;
        this.eurekaClient = eurekaClient;
        this.kafkaTemplate = kafkaTemplate;
        this.kafkaTopic = kafkaTopic;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void sendMetrics() {

        final Map<String, Object> metricsDetails = metricsEndpoint.invoke();
        final Map<String, Object> healthDetails = healthEndpoint.invoke().getDetails();

        final String instanceId = eurekaClient != null ? eurekaClient.getApplicationInfoManager().getInfo().getInstanceId() : null;
        final Long totalMemory = (Long) metricsDetails.getOrDefault("mem", 0);
        final Long freeMemory = (Long) metricsDetails.getOrDefault("mem.free", 0);
        final Integer cpuNumber = (Integer) metricsDetails.getOrDefault("processors", 0);
        final Long totalHeap = (Long) metricsDetails.getOrDefault("heap", 0);
        final Long usedHeap = (Long) metricsDetails.getOrDefault("heap.used", 0);
        final Long startedThreads = (Long) metricsDetails.getOrDefault("threads.totalStarted", 0);

        final Health diskSpaceHealth = (Health) healthDetails.getOrDefault("diskSpace", null);
        final Long totalDiskSpace = (Long) diskSpaceHealth.getDetails().getOrDefault("total", 0);
        final Long freeDiskSpace = (Long) diskSpaceHealth.getDetails().getOrDefault("free", 0);

        final Metrics metricsToSend = Metrics.builder()
            .microserviceId(instanceId)
            .totalMemory(totalMemory)
            .freeMemory(freeMemory)
            .cpuNumber(cpuNumber)
            .totalHeap(totalHeap)
            .usedHeap(usedHeap)
            .startedThreads(startedThreads)
            .totalDiskSpace(totalDiskSpace)
            .freeDiskSpace(freeDiskSpace)
            .build();

        log.info(metricsToSend.toString());
        kafkaTemplate.send(kafkaTopic, metricsToSend);
    }
}
