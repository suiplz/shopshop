package com.example.shopshop.orders.controller;

import com.example.shopshop.orders.service.OrdersItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Log4j2
public class OrdersApiController {

    private final OrdersItemService ordersItemService;

    @PostMapping("/register/{cartId}")
    public ResponseEntity register(@PathVariable Long cartId, @RequestBody Map<String, Object> requestData){

        String impUid = (String) requestData.get("imp_uid");
        int point = Integer.parseInt(requestData.get("point").toString());

        log.info("impUid result : " + impUid);
        log.info("point result : " + point);

        ordersItemService.register(cartId, impUid, point);
        return new ResponseEntity(HttpStatus.OK);

    }

    @PostMapping("/cancelRequest/{ordersItemId}")
    public ResponseEntity cancelRequest(@PathVariable Long ordersItemId) {
        ordersItemService.cancelRequest(ordersItemId);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/manageOrdersItem/{ordersItemId}")
    public ResponseEntity manageOrdersItem(@PathVariable Long ordersItemId, @RequestBody String status) {
        log.info("result : "+ status + " " + status.getClass());
        ordersItemService.manageOrdersStatus(ordersItemId, status);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/completeOrders/{ordersItemId}")
    public ResponseEntity completeOrders(@PathVariable Long ordersItemId, @RequestBody String ordersStatus) throws IOException {

        ordersItemService.complete(ordersItemId, ordersStatus);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/cancelOrders/{ordersItemId}")
    public ResponseEntity cancelOrders(@PathVariable Long ordersItemId, @RequestBody String ordersStatus) throws IOException {

        ordersItemService.cancel(ordersItemId, ordersStatus);
        return new ResponseEntity(HttpStatus.OK);
    }
}
