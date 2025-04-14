package com.shop.dto;

import com.shop.entity.CImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter
@Setter
public class CImgDto {

    // 칵테일 사진 id
    private Long id;

    // 칵테일 이미지 파일명
    private String cImgName;

    // 칵테일 원본 이미지 파일명
    private String oriCImgName;

    // 칵테일 이미지 조회 경로 (Url)
    private String cImgUrl;

    // 대표 이미지 여부
    private String repCImgYn;

    private static ModelMapper modelMapper = new ModelMapper();

    public static CImgDto of(CImg cImg) {
        return modelMapper.map(cImg, CImgDto.class);
    }

}
