package com.esgi.poc.metrics.sender;

import com.netflix.discovery.EurekaClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.MetricsEndpoint;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class MetricsSenderServiceImpl implements MetricsSenderService {

    private final MetricsEndpoint metricsEndpoint;

    private final EurekaClient eurekaClient;

    public MetricsSenderServiceImpl(final MetricsEndpoint metricsEndpoint,
                                    final EurekaClient eurekaClient) {
        this.metricsEndpoint = metricsEndpoint;
        this.eurekaClient = eurekaClient;
    }

    @Override
    @Scheduled(fixedRate = 5000)
    public void sendMetrics() {

        final Map<String, Object> map = metricsEndpoint.invoke();

        for (Map.Entry<String, Object> entry : map.entrySet())
            log.info(entry.getKey() + "/" + entry.getValue());

        log.info(eurekaClient.getApplicationInfoManager().getInfo().getInstanceId());
    }
}
