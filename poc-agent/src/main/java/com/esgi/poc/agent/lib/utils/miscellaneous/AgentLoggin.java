package com.esgi.poc.agent.lib.utils.miscellaneous;

import com.esgi.poc.agent.lib.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;

public class AgentLoggin {
    public static void log(String field,String value){
        Logger logger = LoggerFactory.getLogger(Core.class);
        logger.info(field + " = [" + value + "]");
    }
}
