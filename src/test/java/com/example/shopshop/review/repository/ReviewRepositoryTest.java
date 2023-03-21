package com.example.shopshop.review.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    void findReviewByMemberIdAndItemId() {
        boolean result = reviewRepository.reviewByMemberIdAndItemId(2L, 1L);
        log.info("Result : " + result);
    }



}