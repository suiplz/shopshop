package com.example.shopshop.board.dto;

import com.example.shopshop.comment.dto.CommentForBoardDTO;
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

    private Long memberId;

    @Builder.Default
    private List<CommentForBoardDTO> commentList = new ArrayList<>();

}
