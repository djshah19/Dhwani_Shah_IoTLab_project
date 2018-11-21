package com.me.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication(scanBasePackages = {"com.me.web.service", "com.me.web.models", "com.me.web.repositories", "com.me.web.controller"})
@EnableCaching
public class CartrackerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartrackerApplication.class, args);
    }
}
