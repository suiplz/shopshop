package com.example.shopshop.board.service;

import com.example.shopshop.Item.domain.ItemImage;
import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import com.example.shopshop.board.dto.BoardListDTO;
import com.example.shopshop.board.dto.BoardReadDTO;
import com.example.shopshop.board.repository.BoardRepository;
import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentForBoardDTO;
import com.example.shopshop.comment.repository.CommentRepository;
import com.example.shopshop.page.dto.PageRequestDTO;
import com.example.shopshop.page.dto.PageResultDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
@Log4j2
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
        Function<Object[], BoardListDTO> fn = (arr -> entityToDTOForList(
                (Board) arr[0],
                (Long) arr[1],
                (Long) arr[2],
                (String) arr[3],
                (Long) arr[4]));

        return new PageResultDTO<>(result,fn);
        }


    @Override
    public BoardReadDTO getBoard(Long boardId) {
        List<Object[]> result = boardRepository.getBoardById(boardId);

        List<Comment> commentList = new ArrayList<>();
        result.forEach(arr -> {
            Comment comment = (Comment) arr[4];
            if (comment != null) {
                commentList.add(comment);
            }
        });


        String title = (String) result.get(0)[1];
        String text = (String) result.get(0)[2];
        String email = (String) result.get(0)[3];

        return entityToDTO(boardId, title, text, email, commentList);



    }


    @Override
    public void remove(Long boardId) {

        commentRepository.deleteByBoardId(boardId);
        boardRepository.deleteById(boardId);
    }
}
