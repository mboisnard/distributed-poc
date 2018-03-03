package com.esgi.poc.receiver.lib.core;

import com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules;
import com.esgi.poc.receiver.lib.core.utils.annotations.EnableAgentApplication;
import com.esgi.poc.receiver.lib.core.utils.miscellaneous.AgentInfos;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.lang.annotation.Annotation;

@Slf4j
@Configuration
public class Core {

    public Core(final AgentInfos agentInfos, final ApplicationContext appContext, final Environment environment) {

        appContext.getBeansWithAnnotation(EnableAgentApplication.class).forEach((beanName, beanInstance) -> {

            try {
                final String canonicalName = beanInstance.getClass().getCanonicalName();
                final String className = canonicalName.substring(0, canonicalName.indexOf('$'));
                final Annotation annotation = Class.forName(className).getAnnotation(EnableAgentApplication.class);
                final EnableAgentApplication agentApplication = (EnableAgentApplication) annotation;

                final String searchedIpInProperties = environment.getProperty(agentApplication.ip(), agentApplication.ip());
                final String searchedPortInProperties = environment.getProperty(agentApplication.port(), agentApplication.port());

                agentInfos.setIp(searchedIpInProperties);
                agentInfos.setPort(searchedPortInProperties);

            } catch (final ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        });

        log.info("Kafka ip detected: " + agentInfos.getIp());
        log.info("Kafka port detected: " + agentInfos.getPort());

        appContext.getBeansWithAnnotation(ConfigRules.class).forEach((beanName, beanInstance) -> {

            try {
                final String canonicalName = beanInstance.getClass().getCanonicalName();
                final String className = canonicalName.substring(0, canonicalName.indexOf('$'));
                final Annotation annotation = Class.forName(className).getAnnotation(ConfigRules.class);
                final ConfigRules agentApplication = (ConfigRules) annotation;

                final String searchedTypeInProperties = environment.getProperty(agentApplication.type(), agentApplication.type());

                agentInfos.setType(searchedTypeInProperties);

            } catch (final ClassNotFoundException e) {
                log.error(e.getMessage());
            }
        });

        log.info("Type enabled: " + agentInfos.getType());
    }
}