package com.esgi.poc.receiver.lib.core.utils.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ConfigRules {
    String CPU = "CPU";
    String RAM = "RAM";
    String TIMEOUT = "TIMEOUT";
    String MOSTREVELANT = "MOSTREVELANT";
    String type();
}
