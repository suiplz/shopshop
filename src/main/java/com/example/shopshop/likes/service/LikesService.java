package com.example.shopshop.likes.service;

public interface LikesService {

    Long pushLikes(Long memberId, Long itemId);

    void UnLikes(Long memberId, Long itemId);

    boolean getLikeStates(Long memberId, Long itemId);

    Long getLikesCount(Long itemId);
}
