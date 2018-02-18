package com.esgi.poc.receiver.lib.core;

import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentLogging;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.lang.annotation.Annotation;

public class Core {
    public static void run(Class aClass) {
        Annotation[] annotations = aClass.getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof EnableAgentApplication){
                EnableAgentApplication coredatas = (EnableAgentApplication) annotation;
                String ip = coredatas.ip();
                String port = coredatas.port();

                AgentLogging.log("ip", ip);
                AgentLogging.log("port", port);
            }
        }
    }
}
