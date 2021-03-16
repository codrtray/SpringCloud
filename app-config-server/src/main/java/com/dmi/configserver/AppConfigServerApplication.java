package com.dmi.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableConfigServer
@PropertySource("file:${MY_PATH}/global.properties")
public class AppConfigServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigServerApplication.class, args);
    }

}
