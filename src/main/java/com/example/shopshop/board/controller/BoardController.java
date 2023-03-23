package com.example.shopshop.board.controller;

import com.example.shopshop.Item.dto.ItemDTO;
import com.example.shopshop.Item.service.ItemService;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.service.BoardService;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

    private final BoardService boardService;

    private final ItemService itemService;

    @GetMapping("/boardList/{itemId}")
    public String boardList(PageRequestDTO pageRequestDTO, @PathVariable Long itemId, Model model) {

        ItemDTO itemDTO = itemService.getItem(itemId);
        log.info("itemDTO" + itemDTO);
        PageResultDTO<BoardListDTO, Object[]> result = boardService.getListByItemId(pageRequestDTO, itemId);
        model.addAttribute("itemDTO", itemDTO);
        model.addAttribute("result", result);

        return "board/boardList";
    }
}
