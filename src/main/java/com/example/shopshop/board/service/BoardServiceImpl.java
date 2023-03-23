package com.example.shopshop.board.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.repository.BoardRepository;
import com.example.shopshop.comment.repository.CommentRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.function.Function;

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
    public PageResultDTO<BoardListDTO, Object[]> getListByItemId(PageRequestDTO requestDTO, Long itemId) {

        Pageable pageable = requestDTO.getPageable(Sort.by("id").descending());

        Page<Object[]> result = boardRepository.findListByItemId(pageable, itemId);
        Function<Object[], BoardListDTO> fn = (arr -> entityToDTO(
                (Board) arr[0],
                (Long) arr[1],
                (String) arr[2],
                (Long) arr[3]));

        return new PageResultDTO<>(result,fn);
        }


    @Override
    public void remove(Long itemId, Long boardId) {

        commentRepository.deleteByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }
}
