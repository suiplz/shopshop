package com.example.shopshop.board.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.dto.BoardReadDTO;
import com.example.shopshop.cart.dto.CartItemListDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Test
    void insertBoardTest() {

        Member member = Member.builder().id(1L).build();
        Item item = Item.builder().id(2L).build();

        BoardDTO boardDTO = BoardDTO.builder()
                .title("title2")
                .content("content2")
                .memberId(member.getId())
                .itemId(item.getId())
                .build();

        log.info("result" + boardDTO);
        boardService.register(boardDTO);

    }

    @Test
    void getBoardListByItemIdTest() {

        PageRequestDTO pageRequestDTO = new PageRequestDTO();
        PageResultDTO<BoardListDTO, Object[]> result = boardService.getListByItemId(pageRequestDTO, 3L);

        log.info("result : " + result);

    }

    @Test
    void getBoardTest() {
        BoardReadDTO board = boardService.getBoard(1L);
        log.info("result : " + board);
    }


}