package com.esgi.poc.agent.lib;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
public @interface EnableAgentApplication {
    public boolean enabled() default true;
}
