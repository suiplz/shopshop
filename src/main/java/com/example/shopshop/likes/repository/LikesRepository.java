package com.example.shopshop.likes.repository;

import com.example.shopshop.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying
    @Transactional
    void deleteByMemberIdAndItemId(Long memberId, Long itemId);

    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);

//    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN true ELSE false END " +
//            "FROM Likes l " +
//            "WHERE l.member.id = :memberId AND l.item.id = :itemId")
//    boolean existsByMemberIdAndItemId(Long memberId, Long itemId);

    Long countByMemberId(Long memberId);

    Long countByItemId(Long itemId);


}
