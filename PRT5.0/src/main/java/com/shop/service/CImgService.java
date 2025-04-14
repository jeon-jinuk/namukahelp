package com.shop.service;

import com.shop.entity.CImg;
import com.shop.repository.CImgRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class CImgService {

    @Value("${itemImgLocation}")
    private String cImgLocation;

    private final CImgRepository cImgRepository;
    private final FileService fileService;

    public void saveCImg(CImg cImg, MultipartFile cImgFile) throws Exception {

        String oriCImgName = cImgFile.getOriginalFilename();
        String cImgName = "";
        String cImgUrl = "";

        if (!StringUtils.isEmpty(oriCImgName)) {
            cImgName = fileService.uploadFile(cImgLocation, oriCImgName, cImgFile.getBytes());
            cImgUrl = "/images/item/" + cImgName;
        }

        cImg.updateCImg(oriCImgName, cImgName, cImgUrl);
        cImgRepository.save(cImg);
    }

    public void updatecImg(Long id, MultipartFile cImgFile) throws Exception {
        //  상품 이미지를 수정한 경우 상품 이미지를 업데이트
        if (!cImgFile.isEmpty()) {
            CImg savedCImg = cImgRepository.findById(id)
                    .orElseThrow(EntityNotFoundException::new);
            if (!StringUtils.isEmpty(savedCImg.getCImgName())) {
                fileService.deleteFile(cImgLocation + "/" + savedCImg.getCImgName());
            }

            String oriCImgName = cImgFile.getOriginalFilename();
            String cImgName = fileService.uploadFile(cImgLocation, oriCImgName, cImgFile.getBytes());
            String cImgUrl = "/images/item/" + cImgName;

            savedCImg.updateCImg(oriCImgName, cImgName, cImgUrl);
        }
    }

}
