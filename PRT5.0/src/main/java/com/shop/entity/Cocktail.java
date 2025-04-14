package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.dto.CocktailDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

// 칵테일 메뉴
@Entity
@Table(name = "cocktail")
@Getter
@Setter
@ToString
public class Cocktail {

    @Id //  PK
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;

    // 칵테일 메뉴 이름
    private String cName;

    // 칵테일 메뉴 용량
    private String cCapacity;

    // 칵테일 상세 설명
    @Lob
    @Column(nullable = false)
    private String cDetail;

    // 상품 등록 시간
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date cRegTime;

    // 상품 정보 수정 시간
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime cUpdateTime;

    public void updateCocktail(CocktailDto cocktailDto) {
        this.cName = cocktailDto.getCName();
        this.cCapacity = cocktailDto.getCCapacity();
        this.cDetail = cocktailDto.getCDetail();
    }

}
