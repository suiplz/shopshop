package com.example.shopshop.cart.repository;

import com.example.shopshop.cart.domain.Cart;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT c, ci FROM Cart c " +
            "INNER JOIN CartItem ci ON ci.cart = c " +
            "WHERE c.buyer.id = :id")
    List<Object[]> getCartByMemberId(@Param("id") Long id);

    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT c FROM Cart c " +
            "WHERE c.buyer.id = :memberId")
    Cart findCartByMemberId(@Param("memberId") Long memberId);

    // query error - consider to use querydsl
//    @EntityGraph(attributePaths = {"buyer"}, type = EntityGraph.EntityGraphType.FETCH)
//    @Query("SELECT COUNT(c.id) > 0 " +
//            "FROM Cart c " +
//            "INNER JOIN CartItem ci ON ci.cart = c " +
//            "WHERE c.buyer.id = :buyerId AND ci.item.id = :itemId ")
//    Boolean isAlreadyInCart(@Param("buyerId") Long buyerId, @Param("itemId") Long itemId);



    @Query("SELECT sum(ci.item.price * ci.amount) FROM CartItem ci " +
            "INNER JOIN Cart c ON ci.cart = c " +
            "WHERE c.buyer.id = :id")
    Integer getTotalPriceByMemberId(@Param("id") Long id);

}
