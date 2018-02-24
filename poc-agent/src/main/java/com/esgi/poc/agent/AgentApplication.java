package com.esgi.poc.agent;

import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.esgi.poc"})
@EnableAgentApplication(ip = "192.168.1.1", port = "9092")
public class AgentApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class);
    }
}
