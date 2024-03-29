package com.example.shopshop.orders.repository;

import com.example.shopshop.orders.domain.OrdersHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrdersHistoryRepository extends JpaRepository<OrdersHistory, Long> {

    @Query("SELECT oh, i.id, i.itemName, ii,  oh.member.id, oh.regDate FROM OrdersHistory oh " +
            "INNER JOIN Item i ON oh.item.id = i.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = i.id " +
            "WHERE oh.member.id = :memberId " +
            "GROUP BY oh.id")
    Page<Object[]> getOrdersHistoryByMemberId(Pageable pageable, @Param("memberId") Long memberId);


    @Query("SELECT oh, i.id, i.itemName, ii,  m.id, oh.regDate FROM OrdersHistory oh " +
            "INNER JOIN Item i ON oh.item.id = i.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = i.id " +
            "INNER JOIN Member m ON i.provider.id = m.id " +
            "WHERE m.id = :memberId " +
            "GROUP BY oh.id")
    Page<Object[]> getOrdersHistoryByProviderId(Pageable pageable, @Param("memberId") Long memberId);

    @Query("SELECT CASE WHEN COUNT(oh) > 0 THEN true ELSE false END From OrdersHistory oh " +
            "WHERE oh.member.id = :memberId AND oh.item.id = :itemId ")
    boolean previousOrderedStatus(@Param("memberId") Long memberId, @Param("itemId") Long itemId);
}
