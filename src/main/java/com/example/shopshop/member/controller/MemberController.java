package com.example.shopshop.member.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.member.service.MemberService;
import com.example.shopshop.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public void signup() {

    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute("signupDTO") SignupDTO signupDTO, RedirectAttributes redirectAttributes) {

        Long register = memberService.register(signupDTO);
        redirectAttributes.addFlashAttribute("signupDTO", register);


        return "redirect:/item/list";
    }

    @GetMapping("/login")
    public void login() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal = " + principal);
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id, @LoginCheck Member member, Model model) {

        MemberDTO memberDTO = memberService.get(member.getId());
        model.addAttribute("dto", memberDTO);
        return "/member/info";

    }

//    @GetMapping("/info/{id}")
//    public String info(@PathVariable Long id, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model) {
//        if (principalDetails.isAuthenticated(id)) {
//            MemberDTO memberDTO = memberService.get(id);
//            model.addAttribute("dto", memberDTO);
//            return "/member/info";
//        }
//        return null;
//
//    }

    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, @LoginCheck Member member, Model model) {
        MemberDTO memberDTO = memberService.get(member.getId());
        log.info("memberDTO.getId() : " + memberDTO.getId());
        model.addAttribute("dto", memberDTO);
        return "/member/modify";
    }

    @PostMapping("/modify")
    public String modify(MemberDTO memberDTO) {

        memberService.modify(memberDTO);
        Long id = memberDTO.getId();

        return "redirect:/member/info/" + id;

    }


//    @PostMapping("/login")
//    public String login(RedirectAttributes redirectAttributes) {
//        return "redirect:/item/list";
//    }
}
