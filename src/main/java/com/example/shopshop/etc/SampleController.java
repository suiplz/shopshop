package com.example.shopshop.etc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @GetMapping("/cart")
    public void cart() {

    }

    @GetMapping("/item")
    public void item() {

    }

    @GetMapping("/contact")
    public void contact(){

    }

    @GetMapping("/detail")
    public void detail() {

    }

    @GetMapping("/index")
    public void index() {

    }

    @GetMapping("/shop")
    public void shop() {

    }
}