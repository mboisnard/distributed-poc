package com.esgi.poc.receiver.lib.core.utils.leaderelections;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentInfos;
import com.esgi.poc.receiver.lib.core.utils.stack.StackMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;

import static com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules.*;

@Component
@Slf4j
public class Leader {

    private final AgentInfos agentInfos;

    private static String type;

    private static final String NO_LEADER = "No leader elected";

    public Leader(final AgentInfos agentInfos) {
        this.agentInfos = agentInfos;
        Leader.type = agentInfos.getType();
    }

    public static String elect(final StackMetrics stackMetrics) {

        switch (type) {
            case CPU:
                return sortByCPU(stackMetrics);
            case RAM:
                return sortByRAM(stackMetrics);
            case TIMEOUT:
                return sortByTimeout(stackMetrics);
            case MOSTREVELANT:
                return sortByMostRevelant(stackMetrics);
        }

        return null;
    }

    private static String sortByCPU(final StackMetrics stackMetrics) {
        return getMicroserviceByMinComparator(stackMetrics, Comparator.comparingLong(Metrics::getUsedHeap));
    }

    private static String sortByRAM(final StackMetrics stackMetrics) {

        final Comparator<Metrics> comparator = Comparator.comparingLong(Metrics::getFreeMemory);
        final Collection<Metrics> metrics = stackMetrics.getMetrics().values();

        final AtomicReference<String> maxRamMicroservice = new AtomicReference<>(NO_LEADER);

        metrics.stream()
            .max(comparator)
            .ifPresent(metric -> maxRamMicroservice.set(metric.getMicroserviceId()));

        return maxRamMicroservice.get();
    }

    private static String sortByTimeout(final StackMetrics stackMetrics) {

        return getMicroserviceByMinComparator(
            stackMetrics,
            Comparator.comparingLong(Metrics::getStartedThreads)
        );
    }

    private static String sortByMostRevelant(final StackMetrics stackMetrics) {
        return NO_LEADER;
    }

    private static String getMicroserviceByMinComparator(final StackMetrics stackMetrics,
                                                         final Comparator<Metrics> metricsComparator) {

        final Collection<Metrics> metrics = stackMetrics.getMetrics().values();
        final AtomicReference<String> minValMicroservice = new AtomicReference<>(NO_LEADER);

        metrics.stream()
            .min(metricsComparator)
            .ifPresent(metric -> minValMicroservice.set(metric.getMicroserviceId()));

        return minValMicroservice.get();
    }
}
