package com.example.shopshop.cart.controller;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;
    private final ItemService itemService;

    @PostMapping("/register/{itemId}")
    public ResponseEntity<Long> register(@PathVariable Long itemId, @ModelAttribute CartDTO cartDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        itemService.getItem(itemId);
        cartDTO.setBuyer(principalDetails.getMember());
        Long register = cartService.register(cartDTO);

        return new ResponseEntity<Long>(register, HttpStatus.OK);
    }
}
