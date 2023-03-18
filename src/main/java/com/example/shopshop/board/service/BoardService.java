package com.example.shopshop.board.service;

import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    default Board dtoToEntity(BoardDTO boardDTO) {

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .item(boardDTO.getItem())
                .member(boardDTO.getMember())
                .build();

        return board;
    }

    default BoardDTO entityToDTO(Board board) {

        BoardDTO boardDTO = BoardDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .content(board.getContent())
                .item(board.getItem())
                .member(board.getMember())
                .build();

        return boardDTO;
    }
}
