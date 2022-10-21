package com.example.shopshop.likes.repository;

import com.example.shopshop.likes.domain.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface LikesRepository extends JpaRepository<Likes, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = " INSERT INTO LIKES(member_id, item_id) VALUES (:member_id, :item_id)", nativeQuery = true)
    void mLikes(@Param("member_id") Long memberId, @Param("item_id") Long itemId);

    @Modifying
    @Transactional
    @Query(value = " DELETE FROM LIKES WHERE member_id = :member_id AND item_id = :item_id", nativeQuery = true)
    void mUnlikes(@Param("member_id") Long memberId, @Param("item_id") Long itemId);

}
