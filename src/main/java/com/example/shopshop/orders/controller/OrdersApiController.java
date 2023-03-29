package com.example.shopshop.orders.controller;

import com.example.shopshop.orders.domain.OrdersStatus;
import com.example.shopshop.orders.dto.OrdersItemListDTO;
import com.example.shopshop.orders.service.OrdersService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrdersApiController {

    private final OrdersService ordersService;

    @PostMapping("/register/{cartId}")
    public ResponseEntity register(@PathVariable Long cartId){

        ordersService.register(cartId);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/cancelRequest/{ordersItemId}")
    public ResponseEntity cancelRequest(@PathVariable Long ordersItemId) {
        ordersService.cancelRequest(ordersItemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manageOrdersItem/{ordersItemId}")
    public ResponseEntity manageOrdersItem(@PathVariable Long ordersItemId, @RequestBody String status) {
        log.info("result : "+ status + " " + status.getClass());
        ordersService.manageOrdersStatus(ordersItemId, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/completeOrders/{ordersItemId}")
    public ResponseEntity completeOrders(@PathVariable Long ordersItemId, @RequestBody String ordersStatus) {

        ordersService.complete(ordersItemId, ordersStatus);
        return new ResponseEntity(HttpStatus.OK);
    }
}
