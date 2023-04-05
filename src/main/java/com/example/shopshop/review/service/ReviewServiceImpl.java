package com.example.shopshop.review.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.dto.ReviewListDTO;
import com.example.shopshop.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReviewServiceImpl implements ReviewService {

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
    public PageResultDTO<ReviewListDTO, Object[]> getReviewListByMemberId(PageRequestDTO pageRequestDTO, Long memberId) {
        Pageable pageable = pageRequestDTO.getPageable(Sort.by("id").descending());


        Page<Object[]> result = reviewRepository.getReviewListByMemberId(pageable, memberId);
        Function<Object[], ReviewListDTO> fn = (arr -> (
                entitiesToDTOForList((Long) arr[0],
                        (String) arr[1],
                        (String) arr[2],
                        (Integer) arr[3],
                        (Long) arr[4],
                        (String) arr[5],
                        (ItemImage) arr[6])));

        return new PageResultDTO<>(result, fn);

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

    @Override
    public boolean previousReviewStatus(Long memberId, Long itemId) {
        return reviewRepository.reviewByMemberIdAndItemId(memberId, itemId);
    }
}
