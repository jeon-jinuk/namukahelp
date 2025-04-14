package com.shop.service;

import com.shop.dto.ImgDto;
import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ImgRepository imgRepository;
    private final ImgService imgService;


    // 상품 저장(등록)
    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> ImgFileList) throws Exception {

        // 상품 등록
        Item item = itemFormDto.createItem();
        itemRepository.save(item);

        // 사진 등록
        for (int i = 0; i < ImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItemId(item);
            if (i == 0) {
                itemImg.setRepimgYn("Y");
            } else {
                itemImg.setRepimgYn("N");
            }
            imgService.saveItemImg(itemImg, ImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId) {
        List<ItemImg> imgList =
                imgRepository.findByIdOrderByIdAsc(itemId);
        List<ImgDto> imgDtoList = new ArrayList<>();
        // 조회한 itemImg 엔티티를 ImgDto 객체로 만들어서 리스트에 추가
        for (ItemImg itemImg : imgList) {
            ImgDto imgDto = ImgDto.of(itemImg);
            imgDtoList.add(imgDto);
        }
        // 상품 아이디를 통해 상품 엔티티를 조회
        Item item = itemRepository.findById(itemId)
                .orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = ItemFormDto.of(item);
        itemFormDto.setImgDtoList(imgDtoList);

        return itemFormDto;
    }

    // 삭제
    public void deleteItem(Long id) {

        itemRepository.deleteById(id);
    }

    // 업데이트
    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> imgFileList) throws Exception {

        // 상품 수정
        Item item = itemRepository.findById(itemFormDto.getId())
                .orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> imgIds = itemFormDto.getImgIds();

        // 이미지 등록
        for (int i = 0; i < imgFileList.size(); i++) {
            imgService.updateItemImg(imgIds.get(i), imgFileList.get(i));
        }
        return item.getId();
    }

    // db에 있는 값 list로
    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    public List<ItemImg> getImgList() {
        return imgRepository.findAll();
    }

    public List<ItemFormDto> getTypeList(String type) {
        List<ItemFormDto> itemList = itemRepository.findByType(type);
        itemList.forEach(item -> {
            Long itemId = item.getId();
            List<ItemImg> imgList = imgRepository.findByIdOrderByIdAsc(itemId);
            List<ImgDto> imgDtoList = new ArrayList<>();
            // 조회한 itemImg 엔티티를 ImgDto 객체로 만들어서 리스트에 추가
            for (ItemImg itemImg : imgList) {
                ImgDto imgDto = ImgDto.of(itemImg);
                imgDtoList.add(imgDto);
            }
            item.setImgDtoList(imgDtoList);
        });
        return itemList;
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {
        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

}
