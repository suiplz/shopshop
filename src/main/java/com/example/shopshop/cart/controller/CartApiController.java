package com.example.shopshop.cart.controller;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.cart.dto.CartItemDTO;
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

//    @GetMapping("/cartList/{memberId}")
//    public ResponseEntity getList(@PathVariable Long memberId, @LoginCheck Member member, Model model){
//
//        if (member.getId() == memberId) {
//            List<Object[]> carts = cartService.getCartByMember(member.getId());
//            model.addAttribute("carts", carts);
//            return new ResponseEntity<>(HttpStatus.OK);
//        }
//        return new ResponseEntity<>(HttpStatus.FORBIDDEN);




    @DeleteMapping("/{cartItemId}")
    public ResponseEntity remove(@PathVariable Long cartItemId, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Member member = principalDetails.getMember();
        if (principalDetails.isAuthenticated(member.getId())) {
            Long cartId = cartService.findByMemberId(member.getId()).getId();
            cartService.remove(cartItemId);
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.FORBIDDEN);
    }
}
