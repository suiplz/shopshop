package com.example.shopshop.review.repository;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.review.domain.Review;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Review r " +
            "INNER JOIN Item i ON i.id = r.item.id " +
            "WHERE i.id = :id")
    List<Review> getReviewByItemId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT r FROM Review r " +
            "INNER JOIN Member m ON m.id = r.member.id " +
            "WHERE m.id = :id")
    List<Review> getReviewByMemberId(@Param("id") Long id);
}
