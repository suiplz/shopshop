package com.example.shopshop.comment.repository;

import com.example.shopshop.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Modifying
    @Transactional
    void deleteByBoardId(Long boardId);
}
