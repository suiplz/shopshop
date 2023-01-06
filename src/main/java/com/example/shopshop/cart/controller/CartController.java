package com.example.shopshop.cart.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cartList")
    public void cartList(@LoginCheck Member member, Model model){

        List<Object[]> cartList = cartService.getCartByMember(member.getId());
        //vue spring 동영상 2:01:00 참고 or itemList 처럼
        model.addAttribute("cartList", cartList);

    }
}