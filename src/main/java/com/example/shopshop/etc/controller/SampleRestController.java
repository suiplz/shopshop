package com.example.shopshop.etc.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleRestController {

    @PostMapping("/token")
    public String token() {

        return "<h1>token</h1>";
    }
}
