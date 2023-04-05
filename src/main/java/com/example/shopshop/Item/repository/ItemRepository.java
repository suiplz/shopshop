package com.example.shopshop.Item.repository;

import com.example.shopshop.Item.domain.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r), count(l) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review r on r.item = i " +
            "LEFT OUTER JOIN Likes l on l.item = i " +
            "WHERE i.id = :id GROUP BY ii")
    List<Object[]> getItemDetail(@Param("id") Long id);

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i from Item i " +
            "INNER JOIN Member m ON m.id = i.provider.id " +
            "WHERE m.id = :id")
    List<Item> getItemByMemberId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review  r on r.item = i " +
            "GROUP BY i.id")
    Page<Object[]> getListPage(Pageable pageable);

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review  r on r.item = i " +
            "GROUP BY i.id " +
            "ORDER BY avg(coalesce(r.grade,0)) DESC")
    Page<Object[]> getListPageByRating(Pageable pageable);

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review  r on r.item = i " +
            "WHERE i.itemName LIKE %:itemName% " +
            "GROUP BY i.id")
    Page<Object[]> getListByItemName(Pageable pageable, @Param("itemName") String itemName);


    @EntityGraph(attributePaths = {"category"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "INNER JOIN Category c ON c.id = i.category.id " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review r on r.item = i " +
            "WHERE (:gender IS NULL OR c.gender = :gender) " +
            "AND (:season IS NULL OR c.season = :season) " +
            "AND (:clothType IS NULL OR c.clothType = :clothType)" +
            "GROUP BY i")
    Page<Object[]> getItemByComponents(Pageable pageable, @Param("gender") String gender, @Param("season") String season, @Param("clothType") String clothType);


    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review  r on r.item = i " +
            "WHERE i.provider.id = :providerId " +
            "GROUP BY i.id")
    Page<Object[]> getListByProviderId(Pageable pageable, @Param("providerId") Long providerId);

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r), l.id FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review  r on r.item = i " +
            "INNER JOIN Likes l on l.item.id = i.id " +
            "WHERE l.member.id = :memberId " +
            "GROUP BY i.id")
    Page<Object[]> getListByMemberLikes(Pageable pageable, @Param("memberId") Long memberId);


    @Query("SELECT count(r) From Item i " +
            "LEFT OUTER JOIN Review r ON r.item.id = i.id " +
            "WHERE i.id = :itemId")
    Long getReviewCountByItemId(@Param("itemId") Long itemId);

    @Query("SELECT count(l) From Item i " +
            "LEFT OUTER JOIN Likes l ON l.item.id = i.id " +
            "WHERE i.id = :itemId")
    Long getLikesCountByItemId(@Param("itemId") Long itemId);

}
