package com.example.shopshop.likes.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class LikesServiceTest {


    @Autowired
    private LikesService likesService;


    @Test
    void likesRegisterTest(){

        Long likes = likesService.pushLikes(1L, 9L);
        log.info("Likes : " + likes);
        boolean likeStates1 = likesService.getLikeStates(1L, 9L);
        boolean likeStates2 = likesService.getLikeStates(null, 1L);
        log.info("LikesStates : {}, {}", likeStates1, likeStates2);

    }

    @Test
    void likesDeleteTest() {
        likesService.unLikes(1L,1L);
        likesService.unLikes(1L,2L);
        likesService.unLikes(1L,4L);
        likesService.unLikes(1L,6L);
        likesService.unLikes(1L,8L);
        likesService.unLikes(1L,9L);
        likesService.unLikes(1L,10L);
    }

}