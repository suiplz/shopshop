package com.example.shopshop.comment.repository;

import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentForBoardDTO;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {


    @Query("SELECT new com.example.shopshop.comment.dto.CommentForBoardDTO(c.id, b.id, c.text, m.email, m.id) " +
            "FROM Comment c " +
            "INNER JOIN Board b ON b.id = c.board.id " +
            "INNER JOIN Member m ON c.member.id = m.id " +
            "WHERE b.id = :boardId")
    List<CommentForBoardDTO> getCommentsByBoardId(@Param("boardId") Long boardId);

    @Modifying
    @Transactional
    void deleteByBoardId(Long boardId);
}
