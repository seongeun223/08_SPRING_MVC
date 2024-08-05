package com.ohgiraffers.example.member.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tbl_member")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;

    private String memberId;

    private String password;

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleType role;
}
