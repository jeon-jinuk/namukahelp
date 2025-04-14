package com.shop.service;

import com.shop.dto.CImgDto;
import com.shop.dto.CocktailDto;
import com.shop.entity.CImg;
import com.shop.entity.Cocktail;
import com.shop.entity.Item;
import com.shop.entity.MixItem;
import com.shop.repository.CImgRepository;
import com.shop.repository.CocktailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CocktailService {

    private final CImgService cImgService;
    private final CImgRepository cImgRepository;
    private final CocktailRepository cocktailRepository;
    private final MixItemService mixItemService;

    // 추가(등록)
    public Long saveCocktail(CocktailDto cocktailDto, List<MultipartFile> cImgFileList,
                             List<Item> itemList) throws Exception {

        Cocktail cocktail = cocktailDto.createCocktail();
        cocktailRepository.save(cocktail);

        for (int i = 0; i < cImgFileList.size(); i++) {
            CImg cImg = new CImg();
            cImg.setMenuId(cocktail);
            if (i == 0) {
                cImg.setRepCImgYn("Y");
            } else {
                cImg.setRepCImgYn("N");
            }
            cImgService.saveCImg(cImg, cImgFileList.get(i));
        }

        for (int j = 0; j < itemList.size(); j++) {
            MixItem mixItem = new MixItem();
            mixItem.setMenuId(cocktail);
            mixItemService.saveMixItem(mixItem, itemList.get(j));
        }

        return cocktail.getMenuId();
    }

    // 업데이트(수정)
    public Long updateCocktail(CocktailDto cocktailDto, List<MultipartFile> cImgFileList) throws Exception {

        Cocktail cocktail = cocktailRepository.findById(cocktailDto.getMenuId())
                .orElseThrow(EntityNotFoundException::new);
        cocktail.updateCocktail(cocktailDto);

        List<Long> cImgIds = cocktailDto.getCImgIds();

        for (int i = 0; i < cImgFileList.size(); i++) {
            cImgService.updatecImg(cImgIds.get(i), cImgFileList.get(i));
        }
        return cocktail.getMenuId();
    }

    public List<Cocktail> getCocktailList(){
        return cocktailRepository.findAll();
    }

    // 삭제
    public void deleteCocktail(Long menuId) {
        cocktailRepository.deleteByMenuId(menuId);
    }

    // 조회 리스트
    public CocktailDto getCocktailDtl(Cocktail cocktailId) {
        List<CImg> cImgList = cImgRepository.findByMenuIdOrderByIdAsc(cocktailId);
        List<CImgDto> cImgDtoList = new ArrayList<>();
        for (CImg cImg : cImgList) {
            CImgDto cImgDto = CImgDto.of(cImg);
            cImgDtoList.add(cImgDto);
        }
        // 칵테일 아이디를 통해 칵테일 엔티티 조회
        Cocktail cocktail = cocktailRepository.findById(cocktailId.getMenuId())
                .orElseThrow(EntityNotFoundException::new);
        CocktailDto cocktailDto = CocktailDto.of(cocktail);
        cocktailDto.setCImgDtoList(cImgDtoList);
        return cocktailDto;
    }

}
