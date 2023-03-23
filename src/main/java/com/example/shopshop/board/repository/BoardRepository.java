package com.example.shopshop.board.repository;

import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {


    @EntityGraph(attributePaths = {"item"}, type = EntityGraph.EntityGraphType.FETCH)
    @Query("SELECT b, i.id, m.email, count(c) From Board b " +
            "INNER JOIN Item i ON i.id = b.item.id " +
            "INNER JOIN Member m ON m.id = b.member.id " +
            "LEFT OUTER JOIN Comment c ON c.board.id = b.id " +
            "WHERE b.item.id = :itemId " +
            "GROUP BY b.id")
    Page<Object[]> findListByItemId(Pageable pageable, @Param("itemId") Long itemId);
//
//    @Query("SELECT c, ci, i.id, i.itemName, i.price, ii FROM Cart c " +
//            "INNER JOIN CartItem ci ON ci.cart = c " +
//            "INNER JOIN Item i ON i.id = ci.item.id " +
//            "INNER JOIN ItemImage ii ON ii.item.id = ci.item.id " +
//            "WHERE c.buyer.id = :memberId " +
//            "GROUP BY ci.id")
//    Page<Object[]> getCartByMemberId(Pageable pageable, @Param("memberId") Long memberId);

}
