package com.example.shopshop.Item.repository;

import com.example.shopshop.Item.domain.Item;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @EntityGraph(attributePaths = {"provider"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT i from Item i " +
            "INNER JOIN Member m ON m.id = i.provider.id " +
            "WHERE m.id = :id")
    List<Item> getItemByMemberId(@Param("id") Long id);


}
