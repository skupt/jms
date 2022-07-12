package com.example.jms.app1.pojo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class User {
    private String name;

    @JsonCreator
    public User(@JsonProperty("name") String name) {
        this.name = name;
    }
}
