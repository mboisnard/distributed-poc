package com.esgi.poc.bocet.lib.core;

import com.esgi.poc.bocet.lib.utils.annotations.EnableAgentApplication;
import com.esgi.poc.bocet.lib.utils.miscellaneous.AgentLogging;

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
