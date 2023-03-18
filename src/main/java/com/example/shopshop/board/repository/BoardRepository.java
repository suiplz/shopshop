package com.example.shopshop.board.repository;

import com.example.shopshop.board.domain.Board;
import com.example.shopshop.board.dto.BoardDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<BoardDTO> findByItemId(Long itemId);
}
