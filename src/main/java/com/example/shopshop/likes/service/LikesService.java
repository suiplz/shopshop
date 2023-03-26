package com.example.shopshop.likes.service;

import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LikesService {

    Long pushLikes(Long memberId, Long itemId);

    void unLikes(Long memberId, Long itemId);

    boolean getLikeStates(Long memberId, Long itemId);

    Long getLikesCount(Long itemId);

}
