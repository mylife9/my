package com.ruoyi.cms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @version 1.0
 * @Author 佩奇🍂
 * @Date 2024/7/19 9:46
 * @注释
 */
@Configuration
public class WebsocketConfig {
    @Bean
    public ServerEndpointExporter initWebSocket(){
        return  new ServerEndpointExporter();
    }
}
