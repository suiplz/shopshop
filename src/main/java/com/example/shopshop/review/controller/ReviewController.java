package com.example.shopshop.review.controller;

import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/get/{itemId}")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable Long itemId) {

        List<ReviewDTO> result = reviewService.getListByItemId(itemId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
