package com.esgi.poc.receiver.lib.core.utils.miscellaneous;

import com.esgi.poc.receiver.lib.core.utils.annotations.ConfigRules;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;

@Getter @Setter
@Configuration
public class AgentInfos {

    private String ip;

    private String port;

    private String topic;

    private String type;
}
