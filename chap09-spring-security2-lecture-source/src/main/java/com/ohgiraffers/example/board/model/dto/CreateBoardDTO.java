package com.ohgiraffers.example.board.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class CreateBoardDTO {

    private String title;

    private String content;
}
