package com.example.shopshop.cart.repository;

import com.example.shopshop.cart.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {



    @Query("SELECT c, ci, i.id, i.itemName, i.price, ii FROM Cart c " +
            "INNER JOIN CartItem ci ON ci.cart = c " +
            "INNER JOIN Item i ON i.id = ci.item.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = ci.item.id " +
            "WHERE c.buyer.id = :memberId " +
            "GROUP BY ci.id")
    Page<Object[]> getCartByMemberId(Pageable pageable, @Param("memberId") Long memberId);

    @Query("SELECT c.id, m.id, ci, ii FROM Cart c " +
            "INNER JOIN CartItem ci ON ci.cart = c " +
            "INNER JOIN Item i ON i.id = ci.item.id " +
            "INNER JOIN ItemImage ii ON ii.item.id = ci.item.id " +
            "INNER JOIN Member m ON m.id = c.buyer.id " +
            "WHERE c.id = :cartId " +
            "GROUP BY ci.id")
    List<Object[]> findCartByCartId(@Param("cartId") Long cartId);

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
    Integer getGrandTotalByMemberId(@Param("id") Long id);


}
