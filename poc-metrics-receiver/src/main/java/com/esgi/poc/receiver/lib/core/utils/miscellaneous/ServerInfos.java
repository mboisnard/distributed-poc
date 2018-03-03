package com.esgi.poc.receiver.lib.core.utils.miscellaneous;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
public class ServerInfos {

    private String ip;

    private String port;

    private String topic;
}
