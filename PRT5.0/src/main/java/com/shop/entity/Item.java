package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import com.shop.dto.ItemFormDto;
import com.shop.exception.OutOfStockException;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity{

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;       //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Column(nullable = false)
    private String type; //상품타입

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; //상품 판매 상태

 /*   // 상품 등록 시간
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date regTime;

    // 상품 정보 수정 시간
    @UpdateTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;*/


    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.type = itemFormDto.getType();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }


    //상품주문관련
    public void removeStock(int stockNumber){
        int restStock = this.stockNumber - stockNumber;
        if(restStock<0){
            throw new OutOfStockException("상품의 재고가 부족 합니다. (현재 재고 수량: " + this.stockNumber + ")");
        }
        this.stockNumber = restStock;
    }






    public void addStock(int stockNumber){
        this.stockNumber += stockNumber;
    }

}