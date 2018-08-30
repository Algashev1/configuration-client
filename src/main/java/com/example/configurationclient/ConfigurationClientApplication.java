package com.example.configurationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ConfigurationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationClientApplication.class, args);
    }

    @RefreshScope
    @RestController
    class MessageRestController {

        @Autowired
        private Environment environment;

        @RequestMapping("/parameter")
        String getParameter(@RequestParam("paramName") String paramName) {

            String prop = environment.getProperty(paramName);
            if (prop == null) {
                return "Parameter with such name is not found";
            } else {
                return prop;
            }
        }
    }
}
