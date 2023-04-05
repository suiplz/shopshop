package com.example.shopshop.comment.service;

import com.example.shopshop.comment.dto.CommentDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Log4j2
class CommentServiceTest {

    @Autowired
    private CommentService commentService;

    @Test
    void insertCommentTest() {

        CommentDTO commentDTO1 = CommentDTO.builder()
                .text("comment test 4")
                .boardId(1L)
                .memberId(2L)
                .build();


        commentService.register(commentDTO1);
//        commentService.register(commentDTO2);


    }

}