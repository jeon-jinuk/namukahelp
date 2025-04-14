package com.shop.repository;

import com.shop.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgRepository extends JpaRepository<ItemImg, Long> {


    List<ItemImg> findByIdOrderByIdAsc(Long id);

//    List<ItemImg>


}
