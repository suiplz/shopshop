package com.example.shopshop.comment.controller;

import com.example.shopshop.aop.annotation.LoginCheck;
import com.example.shopshop.comment.dto.CommentDTO;
import com.example.shopshop.comment.service.CommentService;
import com.example.shopshop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/{boardId}")
    public void register(@PathVariable("boardId") Long boardId, @RequestBody CommentDTO commentDTO, @LoginCheck Member member) {

        if (member != null) {
            commentDTO.setBoardId(boardId);
            commentDTO.setMemberId(member.getId());
            commentService.register(commentDTO);
        }

    }

    @DeleteMapping("/{id}")
    public void remove(@PathVariable("id") Long id) {
        commentService.remove(id);
    }
}
