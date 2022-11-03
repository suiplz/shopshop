package com.example.shopshop.review.service;

import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import lombok.extern.log4j.Log4j2;

import java.util.List;

public interface ReviewService {

    Long register(ReviewDTO dto);

    Review modify(ReviewDTO dto);

    List<ReviewDTO> getListByItemId(Long itemId);

    void remove(Long id);

    default Review dtoToEntity(ReviewDTO reviewDTO){
        Review review = Review.builder()
                .title(reviewDTO.getTitle())
                .text(reviewDTO.getText())
                .rate(reviewDTO.getRate())
                .item(reviewDTO.getItem())
                .member(reviewDTO.getMember())
                .build();

        return review;
    }

    default ReviewDTO entityToDTO(Review review) {

        ReviewDTO reviewDTO = ReviewDTO.builder()
                .id(review.getId())
                .title(review.getTitle())
                .text(review.getText())
                .rate(review.getRate())
                .item(review.getItem())
                .member(review.getMember())
                .regDate(review.getRegDate())
                .modDate(review.getModDate())
                .build();

        return reviewDTO;
    }

}
