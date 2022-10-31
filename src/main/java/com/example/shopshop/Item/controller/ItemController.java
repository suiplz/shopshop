package com.example.shopshop.Item.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/register")
    public void register() {

    }

    @PostMapping("/register")
    public String register(ItemDTO itemDTO, RedirectAttributes redirectAttributes) {

        log.info("itemDTO : " + itemDTO);
        Long itemId = itemService.register(itemDTO);

        redirectAttributes.addFlashAttribute("itemDTO", itemId);

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
    public void test(ItemDTO itemDTO, Model model){

        model.addAttribute("result", itemDTO);


    }

}
