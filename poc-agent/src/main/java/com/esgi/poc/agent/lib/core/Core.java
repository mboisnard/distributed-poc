package com.esgi.poc.agent.lib.core;

import com.esgi.poc.agent.lib.utils.annotations.EnableAgentApplication;
import com.esgi.poc.agent.lib.utils.miscellaneous.AgentLoggin;

import java.lang.annotation.Annotation;

public class Core {
    public static void run(Class aClass) {
        Annotation[] annotations = aClass.getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof EnableAgentApplication){
                EnableAgentApplication coredatas = (EnableAgentApplication) annotation;
                String ip = coredatas.ip();
                String port = coredatas.port();

                AgentLoggin.log("ip", ip);
                AgentLoggin.log("port", port);
            }
        }
    }
}
