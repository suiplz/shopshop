package com.example.shopshop.cart.controller;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.cart.dto.CartItemDTO;
import com.example.shopshop.cart.dto.CartItemModifyDTO;
import com.example.shopshop.cart.service.CartService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
@Log4j2
public class CartApiController {

    private final CartService cartService;
    private final ItemService itemService;

    @PostMapping("/register/{itemId}")
    public ResponseEntity register(@PathVariable Long itemId, @RequestBody CartItemDTO cartItemDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) throws Exception {

        Member member = principalDetails.getMember();

        if (principalDetails.isAuthenticated(member.getId())) {
            cartService.register(member, itemId, cartItemDTO);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @PutMapping("/{cartItemId}")
    public ResponseEntity<Long> modify(@PathVariable Long cartItemId, @RequestBody CartItemModifyDTO cartItemModifyDTO) throws Exception{

        log.info("cartItemId On Controller : " + cartItemId);
        log.info("cartItem Modify DTO : " + cartItemModifyDTO);
        cartItemModifyDTO.setId(cartItemId);
        cartService.modify(cartItemId, cartItemModifyDTO);


        return new ResponseEntity(cartItemId, HttpStatus.OK);

    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity remove(@PathVariable Long cartItemId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        cartService.remove(cartItemId);
        return new ResponseEntity(HttpStatus.OK);

    }
}
