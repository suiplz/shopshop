package com.example.shopshop.likes.service;

public interface LikesService {

    void pushLikes(Long memberId, Long itemId);

    void UnLikes(Long memberId, Long itemId);
}
