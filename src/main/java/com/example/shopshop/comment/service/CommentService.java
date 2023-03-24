package com.example.shopshop.comment.service;

import com.example.shopshop.board.domain.Board;
import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentDTO;
import com.example.shopshop.member.domain.Member;

public interface CommentService {

    Long register(CommentDTO commentDTO);

    default Comment dtoToEntity(CommentDTO commentDTO) {

        Board board = Board.builder()
                .id(commentDTO.getBoardId())
                .build();

        Member member = Member.builder()
                .id(commentDTO.getMemberId())
                .build();

        Comment comment = Comment.builder()
                .text(commentDTO.getText())
                .board(board)
                .member(member)
                .build();

        return comment;

    }

    default CommentDTO entityToDTO(Comment comment) {

        CommentDTO commentDTO = CommentDTO.builder()
                .id(comment.getId())
                .text(comment.getText())
                .boardId(comment.getBoard().getId())
                .build();

        return commentDTO;
    }
}
