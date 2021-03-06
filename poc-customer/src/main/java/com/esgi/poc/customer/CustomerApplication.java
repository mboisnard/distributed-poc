package com.esgi.poc.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableEurekaClient
@EnableScheduling
@ComponentScan(basePackages = {"com.esgi.poc"})
@SpringBootApplication
public class CustomerApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }
}
