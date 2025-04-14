package com.shop.service;

import com.shop.entity.Cocktail;
import com.shop.entity.Item;
import com.shop.entity.MixItem;
import com.shop.repository.MixItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Data
public class MixItemService {


    private final MixItemRepository mixItemRepository;

    public void saveMixItem(MixItem mixItem, Item itemList) {

        mixItem.saveMixItem(itemList);
        mixItemRepository.save(mixItem);
    }

    public List<MixItem> getItemList(Cocktail menuId){
        return mixItemRepository.findByMenuId(menuId);
    }

}
