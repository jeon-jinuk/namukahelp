package com.shop.dto;

import com.shop.constant.ItemSellStatus;
import com.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemNm;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotNull(message = "재고 수량은 필수 입력 값입니다.")
    private int stockNumber;

    @NotBlank(message = "상품 상세는 필수 입력 값입니다.")
    private String itemDetail;

    @NotBlank(message = "상품 타입은 필수 입력 값입니다.")
    private String type;

    //상품판매상태
    private ItemSellStatus itemSellStatus;

    //베이스 술 수정시 베이스 술 이미지 정보를 저장하는 리스트
    private List<ImgDto> imgDtoList = new ArrayList<>();

    ///베이스 술 이미지 아이디를 저장하는 리스트
    private List<Long> imgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    //엔티티 객체와 DTO객체 간의 데이터를 복사
    //복사한 객체를 반환
    public Item createItem(){
        return modelMapper.map(this, Item.class);
    }

    public static ItemFormDto of(Item item){
        return modelMapper.map(item,ItemFormDto.class);
    }

}