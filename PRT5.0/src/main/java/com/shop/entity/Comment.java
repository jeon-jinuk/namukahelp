package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

// 댓글
@Entity
@Table(name = "comment")
@Getter
@Setter
@ToString
public class Comment {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    // 댓글 내용
    private String comment;

    // 댓글 작성 시간
    // LocalDate : 날짜만
    // LocalTime : 시간만
    // LocalDateTime : 날짜, 시간
    private LocalDateTime cwriteTime;

    // 댓글 작성자
    private String cwriter;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

}
