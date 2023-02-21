package com.example.javajobjobproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class JavaJobJobProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaJobJobProjectApplication.class, args);
    }

}
