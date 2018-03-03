package com.esgi.poc.receiver.lib.core.utils.stack;

import com.esgi.poc.receiver.lib.core.utils.leaderelections.Leader;
import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
public class StackMetrics extends Stack<String, Metrics> {
    public StackMetrics() {
        stack = new HashMap<>();
    }

    public Map<String, Metrics> getMetrics(){
        return stack;
    }

    public void push(Metrics metrics) {
        stack.getOrDefault(metrics.getMicroserviceId(), stack.put(metrics.getMicroserviceId() , metrics));
        stack.replace(metrics.getMicroserviceId(), metrics);
        log.info(metrics.toString());
    }

    public String getLeader() {
        return Optional.of(Leader.elect(this).toString()).orElse("No leader elected");
    }

}
