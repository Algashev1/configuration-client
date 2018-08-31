package com.example.configurationclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Value("${name}")
    private String name;

    //@Value("#{'${list}'.split(',')}")
    @Value("${list}")
    private List<String> list;

    @Bean
    public String name() {
        return name;
    }

    @Bean
    public List<BigInteger> list() {
        List<BigInteger> newList = new ArrayList<>();
        for (String element: list) {
            newList.add(new BigInteger(element));
        }
        return newList;
    }
}
