package com.example.shopshop.Item.controller;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Gender;
import com.example.shopshop.Item.domain.Season;
import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.category.domain.Category;
import com.example.shopshop.category.service.CategoryService;
import com.example.shopshop.likes.service.LikesService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import com.example.shopshop.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("/item")
@RequiredArgsConstructor
@Log4j2
public class ItemController {

    private final ItemService itemService;

    private final LikesService likesService;

    private final ReviewService reviewService;

    private final CategoryService categoryService;


    @GetMapping("/register")
    public void register(Model model) {
        categoryAttribute(model);
        model.addAttribute("itemDTO", new ItemDTO());

    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult bindingResult, @RequestParam("gender") String gender, @RequestParam("season") String season, @RequestParam("clothType") String clothType, RedirectAttributes redirectAttributes, Model model) {
        log.info("itemDTO : " + itemDTO);
        log.info("gender : " + gender);
        log.info("clothType : " + clothType);
        log.info("season : " + season);

        Category category = categoryService.getCategory(gender, season, clothType);
        itemDTO.setCategory(category);

        Long itemId = itemService.register(itemDTO);


        //검증에 실패하면 다시 입력 폼으로
        if (bindingResult.hasErrors()) {
            log.info("errors = {} ", bindingResult);
            return "item/list";
        }
//        redirectAttributes.addFlashAttribute("itemDTO", itemId);
        redirectAttributes.addAttribute("itemId", itemId);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/item/list";

    }

//    @GetMapping("/list")
//    public void list(PageRequestDTO pageRequestDTO, Model model, @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "season", required = false) String season, @RequestParam(value = "clothType", required = false) String clothType) {
//        categoryAttribute(model);
//
//        model.addAttribute("result", itemService.getList(pageRequestDTO));
//        log.info("result : " + itemService.getList(pageRequestDTO));
//    }
//
//    @PostMapping("/list")
//    public RedirectView list(PageRequestDTO pageRequestDTO, Model model, @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "season", required = false) String season, @RequestParam(value = "clothType", required = false) String clothType) {
//        categoryAttribute(model);
//
//        log.info("category : {}, {}, {}", gender, season, clothType);
//
//        PageResultDTO<ItemDTO, Object[]> result = itemService.getList(pageRequestDTO, gender, season, clothType);
//        model.addAttribute("result", result);
//        log.info("result : " + itemService.getList(pageRequestDTO, gender, season, clothType));
//        return new RedirectView("/item/list");
//    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model,
                       @RequestParam(value = "gender", required = false) String gender,
                       @RequestParam(value = "season", required = false) String season,
                       @RequestParam(value = "clothType", required = false) String clothType) {
        categoryAttribute(model);

        String isNull = "null";

        PageResultDTO<ItemDTO, Object[]> result;
        if (gender == null && season == null && clothType == null) {
            result = itemService.getList(pageRequestDTO);
            log.info("result2: " + result);

        } else {
            if (gender.equals(isNull)) { // 쿼리스트링에 들어가는 null 을 받아서 String "null" 값을 null로 변경
                gender = null;
            }
            if (season.equals(isNull)) {
                season = null;
            }
            if (clothType.equals(isNull)) {
                clothType = null;
            }
            result = itemService.getList(pageRequestDTO, gender, season, clothType);
            log.info("result1: " + result);
        }
        model.addAttribute("result", result);
        log.info("result : " + result);
        return "item/list";
    }

//    @PostMapping("/list")
//    public void list(Model model, @RequestParam(value = "gender", required = false) String gender, @RequestParam(value = "season", required = false) String season, @RequestParam(value = "clothType", required = false) String clothType) {
//        categoryAttribute(model);
//
//    }

    @GetMapping({"/read", "/modify"})
    public void read(Long id, Model model, @LoginCheck Member member) {

        ItemDTO itemDTO = itemService.getItem(id);
        model.addAttribute("dto", itemDTO);
        boolean likesStates = false;
        boolean previousReviewStatus = false;

        if (member != null) {
            likesStates = likesService.getLikeStates(member.getId(), itemDTO.getId());
            previousReviewStatus = reviewService.previousReviewStatus(member.getId(), itemDTO.getId());
            model.addAttribute("member", member);

        }

        model.addAttribute("previousReviewStatus", previousReviewStatus);
        Long likesCount = likesService.getLikesCount(itemDTO.getId());
        model.addAttribute("likesStates", likesStates);
        model.addAttribute("likesCount", likesCount);


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

    private void categoryAttribute(Model model) {
        List<Gender> genders = Arrays.asList(Gender.values());
        List<Season> seasons = Arrays.asList(Season.values());
        List<ClothType> clothTypes = Arrays.asList(ClothType.values());
        model.addAttribute("genders", genders);
        model.addAttribute("seasons", seasons);
        model.addAttribute("clothTypes", clothTypes);
    }

}
