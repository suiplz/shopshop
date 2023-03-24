package com.example.shopshop.comment.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void removeCommentsByBoardIdTest() {
        commentRepository.deleteByBoardId(1L);
    }

}