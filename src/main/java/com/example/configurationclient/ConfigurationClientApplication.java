package com.example.configurationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class ConfigurationClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigurationClientApplication.class, args);
    }

    @RefreshScope
    @RestController
    @RequestMapping ("/test")
    class MessageRestController {

        @Autowired
        private Environment environment;

        @Autowired
        private Configuration configuration;


        @RequestMapping(value="/calculate" , method=RequestMethod.GET )
        String getValue(@RequestParam("operationName") String operationName, @RequestParam("configName") String configName) {
            BigDecimal result = BigDecimal.ZERO;
            if(configuration.getName().equals(configName)) {
                List<BigDecimal> list = configuration.getList();
                if (operationName.equals("max")) {
                    result = Collections.max(list);
                }
                else if (operationName.equals("min")) {
                    result = Collections.min(list);
                }
                else if (operationName.equals("sum")) {
                    for (BigDecimal element: list) {
                        result = result.add(element);
                    }
                }
                else if (operationName.equals("sort")) {
                    Collections.sort(list);
                    return list.toString();
                }
                    return result.toString();
            }
            else {
                return "Configurations with this name are not found";
            }

        }

        @RequestMapping(value="/parameter" , method=RequestMethod.GET )
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
