package com.example.shopshop.review.controller;

import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewApiController {

    private final ReviewService reviewService;

    @GetMapping("/{itemId}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable Long itemId) {

        List<ReviewDTO> result = reviewService.getListByItemId(itemId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/{itemId}")
    public ResponseEntity<Long> register(@RequestBody ReviewDTO reviewDTO) {

        Long reviewnum = reviewService.register(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);

    }

    @PutMapping("/{itemId}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum,
                                             @RequestBody ReviewDTO reviewDTO) {

        reviewService.modify(reviewDTO);
        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}/{reviewnum}")
    public ResponseEntity<Long> removeReview(@PathVariable Long reviewnum) {
        reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }
}
