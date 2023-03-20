package com.example.shopshop.orders.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.service.OrdersService;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/ordersList/{memberId}")
    public String getList(PageRequestDTO pageRequestDTO, @PathVariable Long memberId, @LoginCheck Member member, Model model) {

        PageResultDTO<OrdersItemListDTO, Object[]> result = ordersService.getOrdersByMember(pageRequestDTO, memberId);
        model.addAttribute("result", result);

        return "/orders/ordersList";


    }
}
