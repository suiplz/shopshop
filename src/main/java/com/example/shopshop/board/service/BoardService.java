package com.example.shopshop.board.service;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.Item.dto.ItemImageDTO;
import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.dto.BoardReadDTO;
import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentForBoardDTO;
import com.example.shopshop.member.domain.Member;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface BoardService {

    Long register(BoardDTO boardDTO);

    PageResultDTO<BoardListDTO, Object[]> getListByItemId(PageRequestDTO pageRequestDTO, Long itemId);

    BoardReadDTO getBoard(Long boardId);

    void remove(Long itemId, Long boardId);

    default Board dtoToEntity(BoardDTO boardDTO) {

        Item item = Item.builder()
                .id(boardDTO.getItemId())
                .build();

        Member member = Member.builder()
                .id(boardDTO.getMemberId())
                .build();

        Board board = Board.builder()
                .title(boardDTO.getTitle())
                .content(boardDTO.getContent())
                .item(item)
                .member(member)
                .build();

        return board;
    }

    default BoardListDTO entityToDTOForList(Board board, Long itemId, String memberEmail, Long commentCount) {


        BoardListDTO boardListDTO = BoardListDTO.builder()
                .id(board.getId())
                .title(board.getTitle())
                .itemId(itemId)
                .memberEmail(memberEmail)
                .commentCount(commentCount)
                .build();


        return boardListDTO;
    }

    default BoardReadDTO entityToDTO(Long boardId, String title, String content, String email, List<Comment> comments) {

        BoardReadDTO boardReadDTO = BoardReadDTO.builder()
                .id(boardId)
                .title(title)
                .content(content)
                .email(email)
                .build();

        if (comments != null && comments.size() > 0) {
            List<CommentForBoardDTO> commentList = comments.stream().map(comment -> {
                return CommentForBoardDTO.builder()
                        .id(comment.getId())
                        .text(comment.getText())
                        .email(comment.getMember().getEmail())
                        .build();
            }).collect(Collectors.toList());
            boardReadDTO.setCommentList(commentList);
        }


        return boardReadDTO;


    }

}
