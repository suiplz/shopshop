package com.example.shopshop.review.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService{

    private final ReviewRepository reviewRepository;

    @Override
    public Long register(ReviewDTO dto) {
        Review review = dtoToEntity(dto);
        reviewRepository.save(review);
        return review.getReviewId();
    }

    @Override
    public Review get(Long id) {

        Review review = reviewRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("없는 정보입니다."));
        return review;
    }

    @Override
    public List<ReviewDTO> getListByItemId(Long itemId) {

        Item item = Item.builder().id(itemId).build();

        List<Review> result = reviewRepository.findByItem(item);
        return result.stream().map(itemReview -> entityToDTO(itemReview)).collect(Collectors.toList());

    }

    @Override
    public void modify(ReviewDTO dto) {
        Optional<Review> result = reviewRepository.findById(dto.getReviewId());

        if (result.isPresent()) {

            Review review = result.get();
            review.changeTitle(dto.getTitle());
            review.changeText(dto.getText());
            review.changeGrade(dto.getGrade());

            reviewRepository.save(review);
        }
    }

    @Override
    public void remove(Long id) {
        reviewRepository.deleteById(id);
    }
}
