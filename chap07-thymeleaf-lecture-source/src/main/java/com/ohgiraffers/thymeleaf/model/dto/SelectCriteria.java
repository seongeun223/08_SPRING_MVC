package com.ohgiraffers.thymeleaf.model.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class SelectCriteria {
    private int startPage;
    private int endPage;
    private int pageNo;
}
