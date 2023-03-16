package com.example.shopshop.orders.controller;

import com.example.shopshop.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersApiController {

    private final OrdersService ordersService;

    @PostMapping("/register/{cartId}")
    public ResponseEntity register(@PathVariable Long cartId){

        ordersService.register(cartId);
        return new ResponseEntity(HttpStatus.OK);

    }
}
