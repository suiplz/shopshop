package com.example.shopshop.member.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.member.domain.MemberRole;
import com.example.shopshop.member.dto.MemberDTO;
import com.example.shopshop.member.dto.MemberRoleRequestDTO;
import com.example.shopshop.member.dto.SignupDTO;
import com.example.shopshop.member.dto.SignupMailDTO;
import com.example.shopshop.member.service.MemberService;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Log4j2
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public void signup(Model model) {
        model.addAttribute("signupDTO", new SignupDTO());

    }

    @PostMapping("/signup")
    public String signup(@Validated @ModelAttribute("signupDTO") SignupDTO signupDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "member/signup";
        }

        Long register = memberService.register(signupDTO);
        redirectAttributes.addFlashAttribute("signupDTO", register);


        return "redirect:/item/list";
    }


    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("principal = " + principal);

        String uri = request.getHeader("Referer");
        if (uri != null && !uri.contains("/login")) {
            request.getSession().setAttribute("prevPage", uri);
        }
        return "member/login";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id, @LoginCheck Member member, Model model) {
        List<MemberRole> memberRoles = Arrays.asList(MemberRole.values());
        model.addAttribute("memberRoles", memberRoles);
        if (member.getId() == id) {
            MemberDTO memberDTO = memberService.get(member.getId());
            model.addAttribute("dto", memberDTO);
            log.info("dto : " + memberDTO);

            return "member/info";
        } else {
            return null;
        }

    }



    @GetMapping("/modify/{id}")
    public String modify(@PathVariable Long id, @LoginCheck Member member, Model model) {
        if (member.getId() == id) {
            MemberDTO memberDTO = memberService.get(member.getId());
            log.info("memberDTO.getId() : " + memberDTO.getId());
            model.addAttribute("dto", memberDTO);
            return "member/modify";
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

    @PostMapping("/memberRoleRequest/{memberId}")
    public String memberRoleRequest(@PathVariable("memberId") Long memberId, @RequestBody String role) {

        log.info("clicked" + role);
        memberService.requestRole(memberId, role);
        return "redirect:/member/info/" + memberId;
    }

    @PostMapping("/memberRoleComplete/{id}")
    public String applyMemberRole(@PathVariable Long id, @RequestParam String role) {
        try {
            memberService.applyMemberRole(id, role);
            return "redirect:/member/memberRoleManage";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("/memberRoleManage")
    public String memberRoleManage(PageRequestDTO pageRequestDTO, Model model) {
        List<MemberRole> memberRoles = Arrays.asList(MemberRole.values());
        model.addAttribute("memberRoles", memberRoles);

        PageResultDTO<MemberRoleRequestDTO, Object[]> result = memberService.getRequestMemberRoleList(pageRequestDTO);
        model.addAttribute("result", result);

        return "member/memberRoleManage";

    }

    @GetMapping("/makeNewPassword")
    public void makeNewPassword(){

    }

    @PostMapping("/makeNewPassword")
    public void makeNewPassword(@RequestBody Map<String, Object> requestData){

        String email = requestData.get("email").toString();
        String newPassword = requestData.get("newPassword").toString();
        memberService.makeNewPassword(email, newPassword);
    }


}
