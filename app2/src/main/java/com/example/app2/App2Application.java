package com.example.app2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.example.app2")
public class App2Application {

    public static void main(String[] args) {
        SpringApplication.run(App2Application.class, args);
    }

}
