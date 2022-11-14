package com.example.shopshop.Item.controller;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;


@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemService;

    private final ClothType clothType;

    @GetMapping("/register")
    public void register(Model model) {

        model.addAttribute("itemDTO", new ItemDTO());

    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        ClothType[] values = clothType.values();
        log.info("itemDTO : " + itemDTO);
        Long itemId = itemService.register(itemDTO);


        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "item/list";
        }
        model.addAttribute("clothTypes", values);
//        redirectAttributes.addFlashAttribute("itemDTO", itemId);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/list";

    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model) {

        model.addAttribute("result", itemService.getList(pageRequestDTO));
        log.info("result : " + itemService.getList(pageRequestDTO));
    }

    @GetMapping("/read")
    public void read(Long id, Model model) {

        ItemDTO itemDTO = itemService.getItem(id);
        model.addAttribute("dto", itemDTO);
    }

    @GetMapping("/test")
    public void test(Model model) {
        model.addAttribute("itemDTO", new ItemDTO());
    }

    @PostMapping("/test")
    public void test(@Validated  @ModelAttribute("itemDTO") ItemDTO itemDTO, RedirectAttributes redirectAttributes){

        redirectAttributes.addAttribute("itemId", itemService.register(itemDTO));
        redirectAttributes.addAttribute("status", true);

    }

}
