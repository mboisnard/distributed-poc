package com.esgi.poc.agent;

import com.esgi.poc.receiver.lib.core.Core;
import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import org.springframework.boot.SpringApplication;

@EnableAgentApplication
public class AgentApplication {

    public static void main(String[] args) {
        Core.run(AgentApplication.class);
        SpringApplication.run(Core.class);
    }
}
