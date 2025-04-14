package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "bImg")
@Getter
@Setter
@ToString
public class BImg {

    @Id // PK
    @Column(name = "bImage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bImage_id;

    // 게시판 사진 url
    private String bImg;

}
