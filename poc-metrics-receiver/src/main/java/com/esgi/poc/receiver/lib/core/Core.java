package com.esgi.poc.receiver.lib.core;

import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentLogging;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.ServerInfos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

@Configuration
public class Core {

    @Autowired
    public Core(ServerInfos serverInfos, ApplicationContext appContext) {

        appContext.getBeansWithAnnotation(EnableAgentApplication.class).forEach((s, o) -> {
            try {
                Annotation a = Class.forName(o.getClass().getCanonicalName().substring(0,o.getClass().getCanonicalName().indexOf("$"))).getAnnotation(EnableAgentApplication.class);
                serverInfos.setIp(((EnableAgentApplication)a).ip());
                serverInfos.setPort(((EnableAgentApplication)a).port());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        AgentLogging.log("Kafka ip detected: ", serverInfos.getIp());
        AgentLogging.log("Kafka port detected", serverInfos.getPort());
    }
}