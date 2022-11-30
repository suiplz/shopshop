package com.example.shopshop.likes.service;

import com.example.shopshop.likes.repository.LikesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{

    private final LikesRepository likesRepository;

    @Override
    public void pushLikes(Long memberId, Long itemId) {
        likesRepository.mLikes(memberId, itemId);
    }

    @Override
    public void UnLikes(Long memberId, Long itemId) {
        likesRepository.mUnlikes(memberId, itemId);
    }
}
