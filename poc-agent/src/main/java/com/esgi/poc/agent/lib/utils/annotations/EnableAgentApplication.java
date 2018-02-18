package com.esgi.poc.agent.lib.utils.annotations;

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
    String ip() default "127.0.0.1";
    String port() default "2181";
}
