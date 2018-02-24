package com.esgi.poc.receiver.lib.core.utils.metrics;

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

    public String toString(){
        return "{\n" +
                "\t\"microserviceId\" : " + getMicroserviceId() + ",\n" +
                "\t\"totalMemory\" : " + getTotalMemory() + ",\n" +
                "\t\"freeMemory\" : " + getFreeMemory() + ",\n" +
                "\t\"cpuNumber\" : " + getCpuNumber() + ",\n" +
                "\t\"totalHeap\" : " + getTotalHeap() + ",\n" +
                "\t\"usedHeap\" : " + getUsedHeap() + "," + "\n" +
                "\t\"startedThreads\" : " + getStartedThreads() + ",\n" +
                "\t\"totalDiskSpace\" : " + getTotalDiskSpace() + ",\n" +
                "\t\"freeDiskSpace\" : " + getFreeDiskSpace() + "\n" +
                "}\n";
    }
}
