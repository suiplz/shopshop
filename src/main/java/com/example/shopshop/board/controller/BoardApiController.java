package com.example.shopshop.board.controller;

import com.example.shopshop.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
