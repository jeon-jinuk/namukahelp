package com.shop.repository;

import com.shop.entity.Cocktail;
import com.shop.entity.MixItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MixItemRepository extends JpaRepository<MixItem, Long> {

    List<MixItem> findByMenuId(Cocktail cocktail);

}
