package com.one.springpj.service;

import java.util.List;

import com.one.springpj.constant.MenuType;
import com.one.springpj.model.Menu;

public interface MenuService {

	public void insert(Menu menu);

	List<Menu> menuList();

	public void delete(Long id);

	Menu findById(Long id);

	void update(Menu menu);
	
	//select * from menu where menu_type = "COFFEE";
	public List<Menu> findByMenuType(MenuType menuType);

	public List<Menu> findByMenunameLike(String word);

}
