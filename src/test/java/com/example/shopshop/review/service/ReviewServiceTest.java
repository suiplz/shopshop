package com.example.shopshop.review.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.repository.ItemRepository;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.repository.MemberRepository;
import com.example.shopshop.member.service.MemberService;
import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class ReviewServiceTest {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ItemRepository itemRepository;

//    @BeforeEach
//    void insertData(){
//
//        MemberDTO result = MemberDTO.builder()
//                .email("asd@kkr.com")
//                .password("1231")
//                .name("sad")
//                .phone("123414")
//                .address("s-k-d")
//                .build();
//        memberService.register(result);
//
//
//        Optional<Member> memberById = memberRepository.findById(1L);
//        Member member = memberById.get();
//        Optional<Item> itemById = itemRepository.findById(1L);
//        Item item = itemById.get();
//
//
//        ReviewDTO reviewDTO = ReviewDTO.builder()
//                .title("gooda")
//                .text("neice")
//                .item(item)
//                .member(member)
//                .rate(3)
//                .build();
//
//        reviewService.register(reviewDTO);
//
//
//    }

    @Test
    void insertReview() {
        Optional<Member> result = memberRepository.findById(1L);
        Member member = result.get();
        ReviewDTO reviewDTO = ReviewDTO.builder()
                .member(member)
                .reviewId(10L)
                .grade(4)
                .title("test title")
                .text("test text")
                .build();
        reviewService.register(reviewDTO);

    }
    @Test
    void getReviews() {

        List<ReviewDTO> result = reviewService.getListByItemId(1L);

        result.forEach(itemReview -> {
            System.out.println("itemReview = " + itemReview);
            System.out.println("itemReview.getItem() = " + itemReview.getItem().getId());
            System.out.println("itemReview.getRate() = " + itemReview.getGrade());
        });
    }

    @Test
    void getOneReviewTest() {

//        Optional<Member> result1 = memberRepository.findById(1L);
//        Optional<Item> result2 = itemRepository.findById(1L);
//
//        Member member = result1.get();
//        Item item = result2.get();
//        ReviewDTO reviewDTO = ReviewDTO.builder()
//                .member(member)
//                .item(item)
//                .title("title")
//                .text("asdf")
//                .rate(3)
//                .build();
//        reviewService.register(reviewDTO);

        Review review = reviewService.get(1L);
        log.info("Review : " + review);
        log.info("memberId" + review.getMember().getId());
    }

}