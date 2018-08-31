package com.example.configurationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

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

        @Autowired
        private Configuration configuration;


        @RequestMapping("/calculate")
        String getValue(@RequestParam("operationName") String operationName, @RequestParam("configName") String configName) {
            BigInteger result = BigInteger.ZERO;
            if(configuration.name().equals(configName)) {
                List<BigInteger> list = configuration.list();
                if (operationName.equals("max")) {
                    result = Collections.max(list);
                }
                else if (operationName.equals("min")) {
                    result = Collections.min(list);
                }
                else if (operationName.equals("sum")) {
                    for (BigInteger element: list) {
                        result = result.add(element);
                    }
                }
                return result.toString();
            }
            else {
                return "Configurations with this name are not found";
            }

        }

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
