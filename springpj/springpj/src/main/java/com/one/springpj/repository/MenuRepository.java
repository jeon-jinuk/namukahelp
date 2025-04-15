package com.one.springpj.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.one.springpj.constant.MenuType;
import com.one.springpj.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long>{

	public List<Menu> findByMenuType(MenuType menuType);
	
	public List<Menu> findByNameLike(String word);

	@Query(value="select m from Menu m left join fetch m.cafeMenus where branch_id!=?1")
	public List<Menu> findbyMenuNotCafeMenu(Long branchId);
	
}
