package com.shop.repository;

import com.shop.entity.CImg;
import com.shop.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CImgRepository extends JpaRepository<CImg, Long> {

    List<CImg> findByMenuIdOrderByIdAsc(Cocktail menuId);

}
