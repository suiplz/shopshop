package com.example.shopshop.member.controller;

import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public void signup() {

    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("memberDTO") MemberDTO memberDTO, RedirectAttributes redirectAttributes) {

        Long register = memberService.register(memberDTO);
//        redirectAttributes.addFlashAttribute("memberDTO", register);

        return "/item/list";
    }
}
