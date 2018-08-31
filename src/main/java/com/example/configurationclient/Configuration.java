package com.example.configurationclient;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import java.math.BigDecimal;
import java.util.List;

@org.springframework.context.annotation.Configuration
@ConfigurationProperties(prefix="col")
public class Configuration {

    private String name;

    private List<BigDecimal> list;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BigDecimal> getList() {
        return list;
    }

    public void setList(List<BigDecimal> list) {
        this.list = list;
    }

//    @Bean
//    public String name() {
//        return name;
//    }
//
//    @Bean
//    public List<BigInteger> list() {
//        List<BigInteger> newList = new ArrayList<>();
//        for (String element: list) {
//            newList.add(new BigInteger(element));
//        }
//        return newList;
//    }
}
