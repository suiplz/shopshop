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
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public void register(Model model, @LoginCheck Member member) {
        if (member != null) {
            categoryAttribute(model);
            model.addAttribute("itemDTO", new ItemDTO());
        }

    }

    @PostMapping("/register")
    public String register(@Validated @ModelAttribute ItemDTO itemDTO, BindingResult bindingResult, @RequestParam("gender") String gender, @RequestParam("season") String season, @RequestParam("clothType") String clothType,
                           RedirectAttributes redirectAttributes, Model model, @LoginCheck Member member) {

        if (member != null) {
            Category category = categoryService.getCategory(gender, season, clothType);
            itemDTO.setCategory(category);
            itemDTO.setProvider(member);

            Long itemId = itemService.register(itemDTO);


            //검증에 실패하면 다시 입력 폼으로
            if (bindingResult.hasErrors()) {
                log.info("errors = {} ", bindingResult);
                return "item/list";
            }
//        redirectAttributes.addFlashAttribute("itemDTO", itemId);
            redirectAttributes.addFlashAttribute("itemId", itemId);
            redirectAttributes.addFlashAttribute("status", true);

            return "redirect:/item/list";
        }
        return "redirect:/item/list";
    }

    @GetMapping("/listByRating")
    public String listByRating(PageRequestDTO pageRequestDTO, Model model) {
        categoryAttribute(model);
        PageResultDTO<ItemDTO, Object[]> result = itemService.getListByRating(pageRequestDTO);
        model.addAttribute("result", result);
        return "item/list";
    }

//    @GetMapping("/listByLikesCnt")
//    public String list(PageRequestDTO pageRequestDTO, Model model) {
//        categoryAttribute(model);
//    }

    @GetMapping("/list")
    public String list(PageRequestDTO pageRequestDTO, Model model,
                       @RequestParam(value = "gender", required = false) String gender,
                       @RequestParam(value = "season", required = false) String season,
                       @RequestParam(value = "clothType", required = false) String clothType,
                       @RequestParam(value = "itemName", required = false) String itemName) {
        categoryAttribute(model);

        String isNull = "null";

        PageResultDTO<ItemDTO, Object[]> result;


        if (itemName != null) {

            result = itemService.getList(pageRequestDTO, itemName);
            model.addAttribute("result", result);
            return "item/list";

        }


        if (gender == null && season == null && clothType == null) {
            result = itemService.getList(pageRequestDTO);
            log.info("result2: " + result);

        } else { //else문 빼는것 고려
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

    @GetMapping("/listByProvider/{providerId}")
    public String listByProvider(@PathVariable Long providerId, PageRequestDTO pageRequestDTO, Model model, @LoginCheck Member member) {

        if (member.getId().equals(providerId)) {


            PageResultDTO<ItemDTO, Object[]> result;

            result = itemService.getListByProvider(pageRequestDTO, providerId);

            model.addAttribute("result", result);
            log.info("result : " + result);
            return "item/listByProvider";
        }
        return "redirect:/item/list";
    }

    @GetMapping("/listByMemberLikes/{memberId}")
    public String listByMemberLikes(@PathVariable Long memberId, PageRequestDTO pageRequestDTO, Model model, @LoginCheck Member member) {

        if (member.getId().equals(memberId)) {


            PageResultDTO<ItemDTO, Object[]> result;

            result = itemService.getListByMemberLikes(pageRequestDTO, memberId);

            model.addAttribute("result", result);
            log.info("result : " + result);
            return "item/listByMemberLikes";
        }
        return "redirect:/item/list";
    }


    @GetMapping({"/read", "/modify"})
    public void read(Long id, Model model, @LoginCheck Member member) {

        ItemDTO itemDTO = itemService.getItem(id);
        model.addAttribute("dto", itemDTO);
        log.info("result : " + itemDTO + " " + itemDTO.getId().getClass());
        boolean likesStatus = false;
        boolean previousReviewStatus = false;

        if (member != null) {
            likesStatus = likesService.getLikeStates(member.getId(), itemDTO.getId());
            previousReviewStatus = reviewService.previousReviewStatus(member.getId(), itemDTO.getId());
            model.addAttribute("member", member);

        }

        model.addAttribute("previousReviewStatus", previousReviewStatus);
//        Long likesCount = likesService.getLikesCount(itemDTO.getId());
        model.addAttribute("likesStatus", likesStatus);
//        model.addAttribute("likesCount", likesCount);


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
