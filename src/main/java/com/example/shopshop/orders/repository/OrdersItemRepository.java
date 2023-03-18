package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {

    void deleteByOrdersId(Long ordersId);

    @Query("SELECT sum(oi.totalPrice) From OrdersItem oi " +
            "INNER JOIN Orders o ON o.id = oi.orders.id " +
            "WHERE o.id = :ordersId")
    Integer getGrandTotalByOrdersId(@Param("ordersId") Long ordersId);
}
