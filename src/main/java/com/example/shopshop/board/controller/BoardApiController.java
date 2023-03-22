package com.example.shopshop.board.controller;

import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.repository.BoardRepository;
import com.example.shopshop.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/register/{itemId}")
    public ResponseEntity<Long> register(@PathVariable Long itemId, BoardDTO boardDTO) {

        Long result = boardService.register(boardDTO);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping("/remove/{itemId}/{boardId}")
    public ResponseEntity remove(@PathVariable Long itemId, @PathVariable Long boardId) {

        boardService.remove(itemId, boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
