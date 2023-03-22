package com.example.shopshop.board.service;

import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.repository.BoardRepository;
import com.example.shopshop.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

    private final BoardRepository boardRepository;

    private final CommentRepository commentRepository;

    @Override
    public Long register(BoardDTO boardDTO) {
        Board board = dtoToEntity(boardDTO);
        Board result = boardRepository.save(board);
        return result.getId();
    }

    @Override
    public void remove(Long itemId, Long boardId) {

        commentRepository.deleteByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }
}
