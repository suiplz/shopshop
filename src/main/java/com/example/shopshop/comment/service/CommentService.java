package com.example.shopshop.comment.service;

import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentDTO;

public interface CommentService {

    Long register(CommentDTO commentDTO);

    default Comment dtoToEntity(CommentDTO commentDTO) {

        Comment comment = Comment.builder()
                .text(commentDTO.getText())
                .board(commentDTO.getBoard())
                .build();

        return comment;

    }

    default CommentDTO entityToDTO(Comment comment) {

        CommentDTO commentDTO = CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .board(comment.getBoard())
                .build();

        return commentDTO;
    }
}
