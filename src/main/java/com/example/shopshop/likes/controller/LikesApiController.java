package com.example.shopshop.likes.controller;

import com.example.shopshop.likes.service.LikesService;
import com.example.shopshop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes/api")
@RequiredArgsConstructor
public class LikesApiController {

    private final LikesService likesService;

    @PostMapping("/{memberId}/{itemId}")
    public ResponseEntity<String> pushLikes(@PathVariable("memberId") Long memberId, @PathVariable("itemId") Long itemId, @AuthenticationPrincipal Member member) {
//        if (member.getId() == memberId) {
        likesService.pushLikes(memberId, itemId);
        return new ResponseEntity("Success to Push Likes", HttpStatus.OK);
//        }
//            return new ResponseEntity<>("Failed to Push Likes", HttpStatus.FORBIDDEN);
    }

    @DeleteMapping("/{memberId}/{itemId}")
    public ResponseEntity<String> unPushLikes(@PathVariable("memberId") Long memberId, @PathVariable("itemId") Long itemId, @AuthenticationPrincipal Member member) {
//        if (member.getId() == memberId) {
        likesService.unLikes(memberId, itemId);

        return new ResponseEntity("Success to Push Likes", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("Failed to Push Likes", HttpStatus.FORBIDDEN);
    }


}
