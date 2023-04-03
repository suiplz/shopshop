package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.OrdersItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrdersItemRepository extends JpaRepository<OrdersItem, Long> {

    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT oi, i.id, i.itemName, ii, oi.regDate From OrdersItem oi " +
            "INNER JOIN Item i ON oi.item.id = i.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = i.id " +
            "INNER JOIN Member m ON oi.buyer.id = :memberId " +
            "WHERE m.id = :memberId " +
            "GROUP BY oi.id")
    Page<Object[]> getOrdersByMemberId(Pageable pageable, @Param("memberId") Long memberId);


    @Query("SELECT oi, i.id, i.itemName, ii,  m.id, oi.regDate FROM OrdersItem oi " +
            "INNER JOIN Item i ON oi.item.id = i.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = i.id " +
            "INNER JOIN Member m ON i.provider.id = m.id " +
            "WHERE m.id = :memberId " +
            "GROUP BY oi.id")
    Page<Object[]> getOrdersByProviderId(Pageable pageable, @Param("memberId") Long memberId);

//    @Query("SELECT o, oi, "
//    Page<Object[]> getOrdersListByMemberId(Pageable pageable, @Param("memberId") Long memberId);

//    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT oi.buyer.id, i FROM OrdersItem oi " +
            "INNER JOIN Item i ON i.id = oi.item.id " +
            "WHERE oi.buyer.id = :memberId")
    List<Object[]> getOrdersItemByMemberId(@Param("memberId") Long memberId);

    @Query("SELECT SUM(totalPrice) FROM OrdersItem " +
            "WHERE impUid = :impUid")
    int sumByImpUid(@Param("impUid") String impUid);

//    @EntityGraph(attributePaths = {"ordersItem.item"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT oi.item FROM Orders o " +
//            "INNER JOIN OrdersItem oi ON oi.orders.id = o.id " +
//            "WHERE o.buyer.id = :id")
//    List<Item> getOrdersItemByMemberId(@Param("id") Long id);

}
