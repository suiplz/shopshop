package com.example.shopshop.comment.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentForBoardDTO {

    private Long id;

    private Long boardId;
    private String text;
    private String email;
    private Long memberId;

}
