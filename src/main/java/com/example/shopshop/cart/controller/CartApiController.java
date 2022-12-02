package com.example.shopshop.cart.controller;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.cart.dto.CartDTO;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.member.domain.Member;
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
    public ResponseEntity register(@PathVariable Long itemId, @ModelAttribute CartItemDTO cartItemDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {

        Item item = itemService.findItemById(itemId);
        Member member = principalDetails.getMember();

        if (principalDetails.isAuthenticated(member.getId())) {
            cartService.register(member, item, "S", 1);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
}
