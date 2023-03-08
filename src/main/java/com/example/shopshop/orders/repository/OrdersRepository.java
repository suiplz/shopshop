package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.Orders;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long>  {

    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o FROM Orders o " +
            "INNER JOIN Member m ON o.buyer.id = m.id " +
            "WHERE m.id = :id ")
    List<Orders> getOrdersByMemberId(@Param("id") Long id);

//    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o.buyer.id, i FROM Orders o " +
            "INNER JOIN OrdersItem oi ON oi.orders.id = o.id " +
            "INNER JOIN Item i ON i.id = oi.item.id " +
            "WHERE o.buyer.id = :memberId")
    List<Object[]> getOrdersItemByMemberId(@Param("memberId") Long memberId);



//    @EntityGraph(attributePaths = {"ordersItem.item"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT oi.item FROM Orders o " +
//            "INNER JOIN OrdersItem oi ON oi.orders.id = o.id " +
//            "WHERE o.buyer.id = :id")
//    List<Item> getOrdersItemByMemberId(@Param("id") Long id);

}
