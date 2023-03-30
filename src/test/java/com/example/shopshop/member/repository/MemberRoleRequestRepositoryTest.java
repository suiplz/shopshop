package com.example.shopshop.member.repository;

import com.example.shopshop.page.dto.PageRequestDTO;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class MemberRoleRequestRepositoryTest {

    @Autowired
    private MemberRoleRequestRepository memberRoleRequestRepository;

    @Test
    void listTest() {
        PageRequestDTO requestDTO = new PageRequestDTO();
        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> roleRequestList = memberRoleRequestRepository.getRoleRequestList(pageable);

        for (Object[] objects : roleRequestList) {
            log.info("result " + Arrays.toString(objects));
        }
    }

}