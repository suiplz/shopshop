package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.Orders;
import com.example.shopshop.ordersItem.domain.OrdersItem;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long>  {

    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT o FROM Orders o " +
            "INNER JOIN Member m ON o.buyer.id = m.id " +
            "WHERE m.id = :id ")
    List<Orders> getOrdersByMemberId(@Param("id") Long id);

//    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT oi FROM Orders o " +
            "INNER JOIN OrdersItem oi ON oi.orders.id = o.id " +
            "WHERE o.buyer.id = :id")
    List<OrdersItem> getOrdersItemByMemberId(@Param("id") Long id);
}
