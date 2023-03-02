package com.example.shopshop.cart.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cartList")
    public void cartList(@LoginCheck Member member, Model model){

//        List<Object[]> cartList = cartService.getCartByMember(member.getId());
        //vue spring 동영상 2:01:00 참고 or itemList 처럼
//        model.addAttribute("cartList", cartList);

    }

    @GetMapping("/cartList/{memberId}")
    public String getList(@PathVariable Long memberId, @LoginCheck Member member, Model model) {
//
//        if (member.getId() == memberId) {
//            List<Object[]> cartList = cartService.getCartByMember(member.getId());
//            model.addAttribute("cartList", cartList);
//
//            return "/cart/cartList";
//        }
        return null;
    }
}
