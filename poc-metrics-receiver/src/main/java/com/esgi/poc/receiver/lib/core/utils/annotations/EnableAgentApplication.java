package com.esgi.poc.receiver.lib.core.utils.annotations;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.lang.annotation.*;


@EnableEurekaClient
@SpringBootApplication
@EnableScheduling
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EnableAgentApplication {
    String ip();
    String port();
}
