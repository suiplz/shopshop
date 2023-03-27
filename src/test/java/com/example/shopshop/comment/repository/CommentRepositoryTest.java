package com.example.shopshop.comment.repository;

import com.example.shopshop.comment.dto.CommentForBoardDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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


    @Test
    void getCommentListByBoardId() {

        List<CommentForBoardDTO> commentsByBoardId = commentRepository.getCommentsByBoardId(1L);
        for (CommentForBoardDTO commentForBoardDTO : commentsByBoardId) {
            log.info("result : {}, {}, {}, {}", commentForBoardDTO.getId(), commentForBoardDTO.getBoardId(), commentForBoardDTO.getText(), commentForBoardDTO.getEmail());

        }

    }
}