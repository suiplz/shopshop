package com.example.shopshop.likes.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LikesRepositoryTest {

    @Autowired
    private LikesRepository likesRepository;

    @Test
    void testLikes() {

        likesRepository.mLikes(3L, 1L);
        likesRepository.mLikes(4L, 1L);
        likesRepository.mLikes(5L, 2L);
    }

    @Test
    void testUnLikes() {
        likesRepository.mUnlikes(4L, 1L);
    }

}