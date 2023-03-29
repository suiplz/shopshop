package com.example.shopshop.member.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
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

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

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
    public String login(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal = " + principal);

        String uri = request.getHeader("Referer");
        if ( uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        return "/member/login";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id, @LoginCheck Member member, Model model) {
        List<MemberRole> memberRoles = Arrays.asList(MemberRole.values());
        model.addAttribute("memberRoles", memberRoles);
        if (member.getId() == id) {
            MemberDTO memberDTO = memberService.get(member.getId());
            model.addAttribute("dto", memberDTO);

            return "/member/info";
        } else {
            return null;
        }

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
        if (member.getId() == id) {
            MemberDTO memberDTO = memberService.get(member.getId());
            log.info("memberDTO.getId() : " + memberDTO.getId());
            model.addAttribute("dto", memberDTO);
            return "/member/modify";
        } else {
            return null;
        }
    }

    @PostMapping("/modify")
    public String modify(MemberDTO memberDTO) {

        memberService.modify(memberDTO);
        Long id = memberDTO.getId();

        return "redirect:/member/info/" + id;

    }

    @GetMapping("/memberRoleRequest/{memberId}")
    public String memberRoleRequest(@PathVariable("memberId") Long memberId, @RequestParam("memberRole") String role) {

        log.info("clicked" + role);
        memberService.requestRole(memberId, role);

        return "redirect:/member/info/" + memberId;
    }


//    @PostMapping("/login")
//    public String login(RedirectAttributes redirectAttributes) {
//        return "redirect:/item/list";
//    }
}
