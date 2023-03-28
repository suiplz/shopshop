package com.example.shopshop.board.controller;

import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.dto.BoardReadDTO;
import com.example.shopshop.board.service.BoardService;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    private final ItemService itemService;

    @GetMapping("/register/{itemId}")
    public String register(@LoginCheck Member member, @PathVariable Long itemId){

        if (member != null) {
            return "board/register";
        }

        return null;

    }


    @PostMapping("/register/{itemId}")
    public String register(@ModelAttribute BoardDTO boardDTO, @LoginCheck Member member, @PathVariable Long itemId, Model model) {

        if (member != null) {
            Long memberId = member.getId();
            boardDTO.setMemberId(memberId);
            boardDTO.setItemId(itemId);
            Long result = boardService.register(boardDTO);
            model.addAttribute("itemId", itemId);
            return "redirect:/board/boardList/" + itemId;
        }

        return null;
    }

    @GetMapping("/boardList/{itemId}")
    public String boardList(PageRequestDTO pageRequestDTO, @PathVariable Long itemId, Model model) {

        ItemDTO itemDTO = itemService.getItem(itemId);
        log.info("itemDTO" + itemDTO);
        PageResultDTO<BoardListDTO, Object[]> result = boardService.getListByItemId(pageRequestDTO, itemId);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("result", result);


        return "board/boardList";
    }

    @GetMapping("/read/{boardId}")
    public String getBoard(@PathVariable Long boardId, Model model) {

        BoardReadDTO result = boardService.getBoard(boardId);
        model.addAttribute("result", result);

        log.info("result : " + result);

        return "board/read";
    }
}
