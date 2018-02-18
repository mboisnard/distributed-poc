package com.esgi.poc.bocet.lib.utils.miscellaneous;

import com.esgi.poc.bocet.lib.core.Core;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AgentLogging {
    public static void log(String field,String value){
        Logger logger = LoggerFactory.getLogger(Core.class);
        logger.info(field + " = [" + value + "]");
    }
}
