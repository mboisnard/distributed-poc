package com.esgi.poc.receiver.lib.core.logical;

import com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureAgentRules {
    @Autowired
    public ConfigureAgentRules(ApplicationContext appContext){

        appContext.getBeansWithAnnotation(ConfigRules.class).forEach((s, o) -> {

        });
    }
}
