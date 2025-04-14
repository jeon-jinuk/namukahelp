package com.shop.dto;

import com.shop.entity.Cocktail;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CocktailDto {


    private Long menuId;

    // 칵테일 메뉴 이름
    @NotBlank(message = "메뉴명은 필수값입니다")
    private String cName;

    // 칵테일 메뉴 용량
    @NotBlank(message = "용량은 필수값입니다")
    private String cCapacity;

    // 칵테일 상세 설명
    @NotBlank(message = "상세설명은 필수값입니다")
    private String cDetail;

    // 칵테일 수정시 베이스 술 이미지 정보를 저장하는 리스트
    private List<CImgDto> cImgDtoList = new ArrayList<>();

    // 칵테일 이미지 아이디를 저장하는 리스트
    private List<Long> cImgIds = new ArrayList<>();


    private List<Long> mixIds = new ArrayList<>();

//    private List<Material> materialList=new ArrayList<Material>();

    private static ModelMapper modelMapper = new ModelMapper();

    // 엔티티 객체와 DTO 객체 간의 데이터를 복사.
    // 복사한 객체를 반환

    public Cocktail createCocktail() {
        return modelMapper.map(this, Cocktail.class);
    }

    public static CocktailDto of(Cocktail cocktail) {
        return modelMapper.map(cocktail, CocktailDto.class);
    }
}
