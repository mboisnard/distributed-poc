package com.esgi.poc.receiver.lib.core;

import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.ServerInfos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.lang.annotation.Annotation;

@Slf4j
@Configuration
public class Core {

    public Core(final ServerInfos serverInfos, final ApplicationContext appContext) {

        appContext.getBeansWithAnnotation(EnableAgentApplication.class).forEach((beanName, beanInstance) -> {

            try {
                final String canonicalName = beanInstance.getClass().getCanonicalName();
                final String className = canonicalName.substring(0, canonicalName.indexOf('$'));
                final Annotation annotation = Class.forName(className).getAnnotation(EnableAgentApplication.class);

                serverInfos.setIp(((EnableAgentApplication) annotation).ip());
                serverInfos.setPort(((EnableAgentApplication) annotation).port());

            } catch (final ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        });

        log.info("Kafka ip detected: " + serverInfos.getIp());
        log.info("Kafka port detected: " + serverInfos.getPort());
    }
}