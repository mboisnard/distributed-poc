package com.esgi.poc.bocet.lib.utils.miscellaneous;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AgentLogging {

    public static void log(final String field, final String value) {
        log.info(field + " = [" + value + "]");
    }
}
