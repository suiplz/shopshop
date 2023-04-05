package com.example.shopshop.board.repository;

import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;


    @Test
    void getBoardListByItemIdTest() {
        Long itemId = 2L;
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());
        Page<Object[]> result = boardRepository.findListByItemId(pageable, itemId);

        for (Object[] objects : result) {
            log.info("result : " + Arrays.toString(objects));
        }


    }

    @Test
    void getBoardByIdTest() {
        List<Object[]> result1 = boardRepository.getBoardById(1L);
        List<Object[]> result2 = boardRepository.getBoardById(2L);

        for (Object[] objects : result1) {
            log.info("result : " + Arrays.toString(objects));
        }

        for (Object[] objects : result2) {
            log.info("result : " + Arrays.toString(objects));
        }
    }

}