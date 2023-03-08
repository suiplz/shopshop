package com.example.shopshop.cart.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
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
    public String getList(PageRequestDTO pageRequestDTO, @PathVariable Long memberId, @LoginCheck Member member, Model model) {
//
//        if (member.getId() == memberId) {
//            PageResultDTO<CartItemListDTO, Object[]> result = cartService.getCartByMember(pageRequestDTO, memberId);
//            model.addAttribute("result", result);
//
//            return "/cart/cartList";
//        }

        PageResultDTO<CartItemListDTO, Object[]> result = cartService.getCartByMember(pageRequestDTO, memberId);
        model.addAttribute("result", result);

        return "/cart/cartList";


    }

}
