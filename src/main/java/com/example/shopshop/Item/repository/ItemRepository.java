package com.example.shopshop.Item.repository;

import com.example.shopshop.Item.domain.ClothType;
import com.example.shopshop.Item.domain.Gender;
import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.Season;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

//    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT i, ii FROM Item i " +
//            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
//            "GROUP BY i.id")
//    Page<Object[]> getListPage(Pageable pageable);


//    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
//            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
//            "LEFT OUTER JOIN Review r on r.item = i " +
//            "WHERE i.id = :id")
//    List<Object[]> getItemDetail(@Param("id") Long id);
//    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)

    @Query("SELECT i, ii, avg(coalesce(r.grade, 0)), count(r) FROM Item i " +
            "LEFT OUTER JOIN ItemImage ii on ii.item = i " +
            "LEFT OUTER JOIN Review r on r.item = i " +
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

    @EntityGraph(attributePaths = {"category"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i from Item i " +
            "INNER JOIN Category c ON c.id = i.category.id " +
            "WHERE c.gender = :gender")
    List<Item> getItemByGender(@Param("gender") String gender);

    @EntityGraph(attributePaths = {"category"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i from Item i " +
            "INNER JOIN Category c ON c.id = i.category.id " +
            "WHERE c.season = :season")
    List<Item> getItemBySeason(@Param("season") String season);

    @EntityGraph(attributePaths = {"category"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i from Item i " +
            "INNER JOIN Category c ON c.id = i.category.id " +
            "WHERE c.clothType = :clothType")
    List<Item> getItemByClothType(@Param("clothType") String clothType);


}
