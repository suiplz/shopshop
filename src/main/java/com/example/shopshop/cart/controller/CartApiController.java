package com.example.shopshop.cart.controller;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart/api")
@RequiredArgsConstructor
public class CartApiController {

    private final CartService cartService;

    @PostMapping("/register")
    public ResponseEntity<Long> register(@ModelAttribute CartDTO cartDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        cartDTO.setBuyer(principalDetails.getMember());
        Long register = cartService.register(cartDTO);

        return new ResponseEntity<Long>(register, HttpStatus.OK);
    }
}
