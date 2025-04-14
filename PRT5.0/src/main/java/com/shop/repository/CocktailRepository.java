package com.shop.repository;

import com.shop.entity.Cocktail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<Cocktail, Long> {


    void deleteByMenuId(Long menuId);

}
