package com.esgi.poc.receiver.lib.core;

import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.ServerInfos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;

@Slf4j
@Configuration
public class Core {

    public Core(final ServerInfos serverInfos, final ApplicationContext appContext, final Environment environment) {

        appContext.getBeansWithAnnotation(EnableAgentApplication.class).forEach((beanName, beanInstance) -> {

            try {
                final String canonicalName = beanInstance.getClass().getCanonicalName();
                final String className = canonicalName.substring(0, canonicalName.indexOf('$'));
                final Annotation annotation = Class.forName(className).getAnnotation(EnableAgentApplication.class);
                final EnableAgentApplication agentApplication = (EnableAgentApplication) annotation;

                final String searchedIpInProperties = environment.getProperty(agentApplication.ip(), agentApplication.ip());
                final String searchedPortInProperties = environment.getProperty(agentApplication.port(), agentApplication.port());

                serverInfos.setIp(searchedIpInProperties);
                serverInfos.setPort(searchedPortInProperties);

            } catch (final ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        });

        log.info("Kafka ip detected: " + serverInfos.getIp());
        log.info("Kafka port detected: " + serverInfos.getPort());
    }
}