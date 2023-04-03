package com.example.shopshop.review.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.review.domain.Review;
import com.example.shopshop.review.dto.ReviewDTO;
import com.example.shopshop.review.dto.ReviewListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

//    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT r FROM Review r " +
//            "INNER JOIN Item i ON i.id = r.item.id " +
//            "WHERE i.id = :id")
//    List<Review> getReviewByItemId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Review r " +
            "INNER JOIN Item i ON i.id = r.item.id " +
            "WHERE i =:item")
    List<Review> findByItem(@Param("item") Item item);

    @Query("SELECT r.reviewId, r.title, r.text, r.grade,i.id, i.itemName, ii FROM Review r " +
            "INNER JOIN Member m ON m.id = r.member.id " +
            "INNER JOIN Item i ON i.id = r.item.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = i.id " +
            "WHERE m.id = :memberId " +
            "GROUP BY r.reviewId")
    Page<Object[]> getReviewListByMemberId(Pageable pageable, @Param("memberId") Long memberId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Review r " +
            "WHERE r.member.id = :memberId AND r.item.id = :itemId")
    boolean reviewByMemberIdAndItemId(@Param("memberId") Long memberId, @Param("itemId") Long itemId);
}
