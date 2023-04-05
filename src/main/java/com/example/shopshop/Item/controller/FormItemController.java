package com.example.shopshop.Item.controller;

import com.example.shopshop.Item.domain.ClothType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item/register")
@RequiredArgsConstructor
public class FormItemController {

    @ModelAttribute("clothTypes")
    public ClothType[] clothTypes() {
        return ClothType.values();
    }
}
