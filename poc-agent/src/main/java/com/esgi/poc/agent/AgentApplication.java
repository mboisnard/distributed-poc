package com.esgi.poc.agent;

import com.esgi.poc.agent.lib.EnableAgentApplication;
import org.springframework.boot.SpringApplication;

@EnableAgentApplication()
public class AgentApplication {

    public static void main(String[] args) {
        SpringApplication.run(AgentApplication.class, args);
    }
}
