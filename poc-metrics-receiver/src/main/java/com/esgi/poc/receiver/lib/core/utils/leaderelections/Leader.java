package com.esgi.poc.receiver.lib.core.utils.leaderelections;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentInfos;
import com.esgi.poc.receiver.lib.core.utils.stack.StackMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.Optional;

import static com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules.*;

@Component
@Slf4j
public class Leader {
    private final AgentInfos agentInfos;
    private static String type;

    @Autowired
    public Leader(AgentInfos agentInfos) {
        this.agentInfos = agentInfos;
        Leader.type = agentInfos.getType();
    }

    public static String elect(StackMetrics stackMetrics){
        switch (type){
            case CPU:
                return Optional.of(sortByCPU(stackMetrics)).get().orElse("No leader elected");
            case RAM:
                return Optional.of(Optional.of(sortByRAM(stackMetrics)).get()).orElse("No leader elected");
            case TIMEOUT:
                return Optional.of(sortByTimeout(stackMetrics)).get().orElse("No leader elected");
            case MOSTREVELANT:
                return Optional.of(Optional.of(sortByMostRevelant(stackMetrics)).get()).orElse("No leader elected");
        }
        return null;
    }

    private static Optional<String> sortByCPU(StackMetrics stackMetrics){
        final Comparator<Metrics> comp = Comparator.comparingLong(Metrics::getUsedHeap);
        if(stackMetrics.getMetrics().values().stream().min(comp).isPresent())
            return Optional.of(stackMetrics.getMetrics().values().stream().min(comp).get().getMicroserviceId());
        return Optional.empty();
    }

    private static String sortByRAM(StackMetrics stackMetrics){
        final Comparator<Metrics> comp = Comparator.comparingLong(Metrics::getFreeMemory);
        return String.valueOf(stackMetrics.getMetrics().values().stream().max(comp).get().getMicroserviceId());
    }

    private static Optional<String> sortByTimeout(StackMetrics stackMetrics){
        final Comparator<Metrics> comp = Comparator.comparingLong(Metrics::getStartedThreads);
        if(stackMetrics.getMetrics().values().stream().min(comp).isPresent())
            return Optional.of(stackMetrics.getMetrics().values().stream().min(comp).get().getMicroserviceId());
        else return Optional.empty();
    }

    private static String sortByMostRevelant(StackMetrics stackMetrics){
        return "";
    }
}
