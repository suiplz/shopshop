package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.OrdersItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {

    void deleteByOrdersId(Long ordersId);
}
