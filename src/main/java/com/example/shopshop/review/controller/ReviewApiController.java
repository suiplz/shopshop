package com.example.shopshop.review.controller;

import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.service.ReviewService;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewApiController {

    private final ReviewService reviewService;
    private final ItemService itemService;

    @GetMapping("/{itemId}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable Long itemId) {

        List<ReviewDTO> result = reviewService.getListByItemId(itemId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<Long> register(@PathVariable Long itemId, @RequestBody ReviewDTO reviewDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Member member = principalDetails.getMember();
        if (principalDetails.isAuthenticated(member.getId()) && !reviewService.previousReviewStatus(member.getId(), itemId)) {

            reviewDTO.setMember(member);
            reviewDTO.setItem(itemService.findItemById(itemId));
            Long reviewnum = reviewService.register(reviewDTO);

            return new ResponseEntity<>(reviewnum, HttpStatus.OK);
        }

        return new ResponseEntity(-1, HttpStatus.UNAUTHORIZED);

    }

    @PutMapping("/{itemId}/{reviewId}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewId,
                                             @RequestBody ReviewDTO reviewDTO) {

        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}/{reviewId}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewId) {
        reviewService.remove(reviewId);

        return new ResponseEntity<>(reviewId, HttpStatus.OK);
    }
}
