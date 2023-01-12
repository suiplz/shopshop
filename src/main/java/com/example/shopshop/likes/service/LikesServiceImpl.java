package com.example.shopshop.likes.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.likes.domain.Likes;
import com.example.shopshop.likes.repository.LikesRepository;
import com.example.shopshop.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LikesServiceImpl implements LikesService{

    private final LikesRepository likesRepository;

    @Transactional
    @Override
    public Long pushLikes(Long memberId, Long itemId) {

        Likes likes = Likes.builder()
                .member(Member.builder().id(memberId).build())
                .item(Item.builder().id(itemId).build()).build();
        likesRepository.save(likes);

        return likes.getId();
    }

    @Transactional
    @Override
    public void UnLikes(Long memberId, Long itemId) {

        likesRepository.deleteByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    public boolean getLikeStates(Long memberId, Long itemId) {
        return likesRepository.existsByMemberIdAndItemId(memberId, itemId);
    }

    @Override
    public Long getLikesCount(Long itemId) {
        return likesRepository.countByItemId(itemId);
    }
}
