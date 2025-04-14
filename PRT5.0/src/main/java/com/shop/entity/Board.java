package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

// 게시판
@Entity
@Table(name = "Board")
@Getter
@Setter
@ToString
public class Board {

    @Id // PK 설정
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long board_id;

    // 제목
    private String title;

    // 내용
    private String content;

    // 시간
    private LocalDate writeTime;
    private LocalDate updateTime;

    // 조회수
    private int hit;

    // 추천수
    private int recommend;

    // 멤버 아이디 다대일 매핑
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;



    @ManyToOne
    @JoinColumn(name = "bImage_id")
    private BImg bImg;



}
