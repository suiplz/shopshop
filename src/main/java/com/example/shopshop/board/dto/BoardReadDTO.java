package com.example.shopshop.board.dto;

import com.example.shopshop.Item.domain.Item;
import com.example.shopshop.comment.domain.Comment;
import com.example.shopshop.comment.dto.CommentForBoardDTO;
import com.example.shopshop.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardReadDTO {

    private Long id;

    private String title;

    private String content;

    private String email;

    @Builder.Default
    private List<CommentForBoardDTO> commentList = new ArrayList<>();

}
