package com.example.shopshop.review.service;

import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;

import java.util.List;

public interface ReviewService {

    Long register(ReviewDTO dto);

    Review get(Long id);

    void modify(ReviewDTO dto);

    List<ReviewDTO> getListByItemId(Long itemId);

    void remove(Long id);

    boolean previousReviewStatus(Long memberId, Long itemId);

    default Review dtoToEntity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .title(reviewDTO.getTitle())
                .text(reviewDTO.getText())
                .grade(reviewDTO.getGrade())
                .item(reviewDTO.getItem())
                .member(reviewDTO.getMember())
                .build();

        return review;
    }

    default ReviewDTO entityToDTO(Review review) {

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .reviewId(review.getReviewId())
                .title(review.getTitle())
                .text(review.getText())
                .grade(review.getGrade())
                .item(review.getItem())
                .member(review.getMember())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }

}
