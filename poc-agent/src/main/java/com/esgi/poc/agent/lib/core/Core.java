package com.esgi.poc.agent.lib.core;

import com.esgi.poc.agent.lib.utils.annotations.EnableAgentApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class Core {
    public static void run(Class aClass) {
        Logger logger = LoggerFactory.getLogger(Core.class);
        Annotation[] annotations = aClass.getAnnotations();

        for(Annotation annotation : annotations){
            if(annotation instanceof EnableAgentApplication){
                EnableAgentApplication coreDatas = (EnableAgentApplication) annotation;
                String ip = coreDatas.ip();
                String port = coreDatas.port();
                logger.info("ip = [" + ip + "]");
                logger.info("port = [" + port + "]");
            }
        }
    }
}
