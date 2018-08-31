package com.example.configurationclient;


import org.springframework.boot.context.properties.ConfigurationProperties;
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

}
