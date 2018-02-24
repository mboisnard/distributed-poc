package com.esgi.poc.receiver.lib.core.utils.miscellaneous;

import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerInfos {
    private String ip;
    private String port;
    private String topic;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
