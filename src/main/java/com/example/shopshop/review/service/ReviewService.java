package com.example.shopshop.review.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.dto.ReviewListDTO;

import java.util.List;

public interface ReviewService {

    Long register(ReviewDTO dto);

    Review get(Long id);

    void modify(ReviewDTO dto);

    List<ReviewDTO> getListByItemId(Long itemId);

    PageResultDTO<ReviewListDTO, Object[]> getReviewListByMemberId(PageRequestDTO pageRequestDTO, Long memberId);

    void remove(Long id);

    boolean previousReviewStatus(Long memberId, Long itemId);

    default Review dtoToEntity(ReviewDTO reviewDTO) {
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

    default ReviewListDTO entitiesToDTOForList(Long reviewId, String title, String text, Integer grade, Long itemId, String itemName, ItemImage itemImage) {

        ItemImageDTO itemImageDTO = new ItemImageDTO().builder()
                .imgName(itemImage.getImgName())
                .path(itemImage.getPath())
                .uuid(itemImage.getUuid())
                .build();

        ReviewListDTO reviewListDTO = ReviewListDTO.builder()
                .reviewId(reviewId)
                .title(title)
                .text(text)
                .grade(grade)
                .itemId(itemId)
                .itemName(itemName)
                .itemImage(itemImageDTO)
                .build();

        return reviewListDTO;

    }

}
