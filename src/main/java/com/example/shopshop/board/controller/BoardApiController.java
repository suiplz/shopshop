package com.example.shopshop.board.controller;

import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.repository.BoardRepository;
import com.example.shopshop.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;


    @DeleteMapping("/remove/{boardId}")
    public ResponseEntity remove(@PathVariable Long boardId) {

        boardService.remove(boardId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
