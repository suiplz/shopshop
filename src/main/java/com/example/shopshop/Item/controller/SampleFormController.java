package com.example.shopshop.Item.controller;

import com.example.shopshop.Item.domain.ClothType;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sample")
@RequiredArgsConstructor
public class SampleFormController {


    @GetMapping("/login")
    public void login(Model model) {
        model.addAttribute(ClothType.values());

    }
}
