package com.esgi.poc.receiver.lib.core.utils.stack;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class StackMicroservices extends Stack<String, StackMetrics> {

    public StackMicroservices() {
        stack = new HashMap<>();
    }

    public Map<String, StackMetrics> getMicroservices() {
        return stack;
    }

    public void push(final Metrics metrics) {

        stack.getOrDefault(
            metrics.getGroupId(),
            stack.put(metrics.getGroupId(), new StackMetrics())
        );

        stack.get(metrics.getGroupId())
            .push(metrics);
    }
}
