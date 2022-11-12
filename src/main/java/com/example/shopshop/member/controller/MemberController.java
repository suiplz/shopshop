package com.example.shopshop.member.controller;

import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpRequest;
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

    }

//    @PostMapping("/login")
//    public String login(RedirectAttributes redirectAttributes) {
//        return "redirect:/item/list";
//    }
}
