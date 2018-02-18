package com.esgi.poc.bocet.lib.core;

import com.esgi.poc.bocet.lib.utils.annotations.EnableAgentApplication;
import com.esgi.poc.bocet.lib.utils.miscellaneous.AgentLogging;

import java.lang.annotation.Annotation;

public class Core {

    public static void run(final Class aClass) {

        final Annotation[] annotations = aClass.getAnnotations();

        for (final Annotation annotation : annotations) {

            if(annotation instanceof EnableAgentApplication) {
                final EnableAgentApplication coredatas = (EnableAgentApplication) annotation;
                final String ip = coredatas.ip();
                final String port = coredatas.port();

                AgentLogging.log("ip", ip);
                AgentLogging.log("port", port);
            }
        }
    }
}
