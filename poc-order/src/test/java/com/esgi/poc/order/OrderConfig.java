package com.esgi.poc.order;

import com.netflix.discovery.EurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class OrderConfig {

    @Bean
    public EurekaClient eurekaClient() {
        return mock(EurekaClient.class);
    }
}
