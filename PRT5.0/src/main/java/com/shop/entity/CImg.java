package com.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "cImg")
@Getter
@Setter
@ToString
public class CImg {

    @Id // PK
    @Column(name = "cImage_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 칵테일 이미지 파일명
    private String cImgName;

    // 칵테일 원본 이미지 파일명
    private String oriCImgName;

    // 칵테일 이미지 조회 경로 (Url)
    private String cImgUrl;

    // 대표 이미지 여부
    private String repCImgYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "menu_id")
    private Cocktail menuId;

    // 칵테일 이미지 업데이트
    public void updateCImg(String oriCImgName, String cImgName, String cImgUrl) {
        this.oriCImgName = oriCImgName;
        this.cImgName = cImgName;
        this.cImgUrl = cImgUrl;
    }

}
