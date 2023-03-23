package com.example.shopshop.board.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    PageResultDTO<BoardListDTO, Object[]> getListByItemId(PageRequestDTO pageRequestDTO, Long itemId);

    void remove(Long itemId, Long boardId);

    default Board dtoToEntity(BoardDTO boardDTO) {

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .item(boardDTO.getItem())
                .member(boardDTO.getMember())
                .build();

        return board;
    }

    default BoardListDTO entityToDTO(Board board, Long itemId, String memberEmail, Long commentCount) {


        BoardListDTO boardListDTO = BoardListDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .itemId(itemId)
                .memberEmail(memberEmail)
                .commentCount(commentCount)
                .build();


        return boardListDTO;
    }


}
