package com.example.shopshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ShopshopApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShopshopApplication.class, args);
    }

}
