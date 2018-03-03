package com.esgi.poc.agent;

import com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules;
import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = {"com.esgi.poc"})
@EnableAgentApplication(ip = "kafka.server.ip", port = "kafka.server.port", topic = "kafka.topic.name")
@ConfigRules(type = ConfigRules.CPU)
public class AgentApplication {

    public static void main(final String[] args) {
        SpringApplication.run(AgentApplication.class);
    }
}
