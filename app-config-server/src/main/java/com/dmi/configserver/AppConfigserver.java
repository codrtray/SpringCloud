package com.dmi.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class AppConfigserver {
    public static void main(String[] args) {
        SpringApplication.run(AppConfigserver.class, args);
    }

}
