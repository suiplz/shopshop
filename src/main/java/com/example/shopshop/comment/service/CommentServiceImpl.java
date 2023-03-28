package com.example.shopshop.comment.service;

import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentDTO;
import com.example.shopshop.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

    private final CommentRepository commentRepository;

    @Override
    public Long register(CommentDTO commentDTO) {
        Comment comment = dtoToEntity(commentDTO);
        Comment result = commentRepository.save(comment);
        return result.getId();
    }

    @Override
    public void remove(Long id) {
        commentRepository.deleteById(id);
    }
}
