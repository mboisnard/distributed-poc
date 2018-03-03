package com.esgi.poc.receiver.lib.core.utils.stack;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;

import java.util.HashMap;

public class StackMicroservices extends Stack<String, StackMetrics> {
    public StackMicroservices(){
        stack = new HashMap<>();
    }

    public void push(Metrics metrics){
        stack.getOrDefault(metrics.getGroupId(), stack.put(metrics.getGroupId(), new StackMetrics()));
        stack.get(metrics.getGroupId()).push(metrics);
    }
}
