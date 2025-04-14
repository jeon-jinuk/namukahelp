package com.shop.service;

import com.shop.entity.ItemImg;
import com.shop.repository.ImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ImgService {

    @Value("${itemImgLocation}")
    private String imgLocation;

    private final ImgRepository imgRepository;

    private final FileService fileService;

    public void saveItemImg(ItemImg itemImg, MultipartFile imgFile) throws Exception {

        String oriImgName = imgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        // 파일 업로드
        if (!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(imgLocation, oriImgName, imgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        // 상품 이미지 정보 저장
        itemImg.updateItemImg(oriImgName, imgName, imgUrl);
        imgRepository.save(itemImg);
    }

    public void updateItemImg(Long id, MultipartFile imgFile) throws Exception {
        //  상품 이미지를 수정한 경우 상품 이미지를 업데이트
        if (!imgFile.isEmpty()) {
            ItemImg savedItemImg = imgRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            if (!StringUtils.isEmpty(savedItemImg.getImgName())) {
                fileService.deleteFile(imgLocation + "/" + savedItemImg.getImgName());
            }

            String oriImgName = imgFile.getOriginalFilename();
            String aImgName = fileService.uploadFile(imgLocation, oriImgName, imgFile.getBytes());
            String aImgUrl = "/images/item/" + aImgName;

            savedItemImg.updateItemImg(oriImgName, aImgName, aImgUrl);
        }
    }

    public List<ItemImg> getItemImageList() {
        return imgRepository.findAll();
    }

}
