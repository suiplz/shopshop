package com.example.shopshop.review.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import com.example.shopshop.review.dto.ReviewListDTO;
import com.example.shopshop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
@Log4j2
public class reviewController {

    private final ReviewService reviewService;

    @GetMapping("/reviewListByMember/{memberId}")
    public String reviewListByMember(@PathVariable("memberId") Long memberId, @LoginCheck Member member, Model model) {

        if (member.getId().equals(memberId)) {
            PageRequestDTO pageRequestDTO = new PageRequestDTO();
            PageResultDTO<ReviewListDTO, Object[]> result = reviewService.getReviewListByMemberId(pageRequestDTO, memberId);
            model.addAttribute("result", result);
            log.info("result : " + result);
//itemImage=ItemImageDTO(uuid=b2990bbb-0c0d-4806-9eb9-9d6cc4847843, imgName=xavier-cee-y8PkyBEhWfw-unsplash.jpg, path=2023\03\27))
            return "review/reviewListByMember";
        }
        return "redirect:/item/list";


    }

}
