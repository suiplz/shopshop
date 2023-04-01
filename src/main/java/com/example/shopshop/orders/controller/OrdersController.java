package com.example.shopshop.orders.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.OrdersHistoryListDTO;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.service.OrdersService;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/orders")
@RequiredArgsConstructor
@Log4j2
public class OrdersController {

    private final OrdersService ordersService;

    @GetMapping("/ordersList/{memberId}")
    public String getList(PageRequestDTO pageRequestDTO, @PathVariable Long memberId, @LoginCheck Member member, Model model) {

        if (member != null) {
            PageResultDTO<OrdersItemListDTO, Object[]> result = ordersService.getOrdersByMember(pageRequestDTO, memberId);
            model.addAttribute("result", result);
            log.info("result : " + result);

            model.addAttribute("memberId", memberId);

            return "/orders/ordersList";
        }
        return null;


    }

    @GetMapping("/ordersManage/{providerId}")
    public String getManagePage(PageRequestDTO pageRequestDTO, @PathVariable Long providerId, @LoginCheck Member member, Model model) {

        List<OrdersStatus> ordersStatus = Arrays.asList(OrdersStatus.values());
        model.addAttribute("ordersStatus", ordersStatus);
        PageResultDTO<OrdersItemListDTO, Object[]> result = ordersService.getOrdersByProvider(pageRequestDTO, providerId);
        model.addAttribute("result", result);

        return "/orders/ordersManage";


    }

    @GetMapping("/ordersHistory/{memberId}")
    public String getListByMember(PageRequestDTO pageRequestDTO, @PathVariable Long memberId, @LoginCheck Member member, Model model) {

        PageResultDTO<OrdersHistoryListDTO, Object[]> result = ordersService.getOrdersHistoryByMember(pageRequestDTO, memberId);
        model.addAttribute("result", result);

        return "/orders/ordersHistory";


    }

    @GetMapping("/ordersHistoryProvider/{providerId}")
    public String getListByProvider(PageRequestDTO pageRequestDTO, @PathVariable Long providerId, @LoginCheck Member member, Model model) {

        PageResultDTO<OrdersHistoryListDTO, Object[]> result = ordersService.getOrdersHistoryByProvider(pageRequestDTO, providerId);
        log.info("result444  : " +  result);
        model.addAttribute("result", result);

        return "/orders/ordersHistoryProvider";


    }
}
