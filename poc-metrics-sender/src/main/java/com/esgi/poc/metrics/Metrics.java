package com.esgi.poc.metrics;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Metrics {

    private String microserviceId;

    private Long totalMemory;

    private Long freeMemory;

    private Integer cpuNumber;

    private Long totalHeap;

    private Long usedHeap;

    private Long startedThreads;

    private Long totalDiskSpace;

    private Long freeDiskSpace;
}
