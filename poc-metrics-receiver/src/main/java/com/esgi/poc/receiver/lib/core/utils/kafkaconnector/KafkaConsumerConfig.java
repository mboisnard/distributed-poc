package com.esgi.poc.receiver.lib.core.utils.kafkaconnector;

import com.esgi.poc.receiver.lib.core.utils.metrics.Metrics;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentInfos;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Configuration
class KafkaConsumerConfig {

    private final AgentInfos agentInfos;

    private static final String CONSUMER_GROUP = "consumerGroup#1";

    KafkaConsumerConfig(final AgentInfos agentInfos) {
        this.agentInfos = agentInfos;
    }

    @Bean
    public Map<String, Object> consumerConfigs() {

        final String bootstrapServers = agentInfos.getIp() + ":" + agentInfos.getPort();

        final Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP);

        return props;
    }

    @Bean
    public ConsumerFactory<String, Metrics> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs(),
            new StringDeserializer(), new JsonDeserializer<>(Metrics.class));
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Metrics>> kafkaListenerContainerFactory() {
        final ConcurrentKafkaListenerContainerFactory<String, Metrics> factory =
            new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }
}
