package com.one.springpj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.one.springpj.constant.MenuType;
import com.one.springpj.model.Menu;
import com.one.springpj.repository.MenuRepository;

import lombok.extern.java.Log;

@Service
@Log
public class ManuServiceImpl implements MenuService {
	
	@Autowired
	private MenuRepository menuRepository;

	@Override
	public void insert(Menu menu) {
		// TODO Auto-generated method stub
		menuRepository.save(menu);

	}

	@Override
	public List<Menu> menuList() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return menuRepository.findAll(sort);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		log.info("service id : ========" + id);
		menuRepository.deleteById(id);

	}

	@Override
	public Menu findById(Long id) {
		// TODO Auto-generated method stub
		Menu menu = menuRepository.findById(id).get();
		return menu;
	}

	@Override
	public void update(Menu menu) {
		// TODO Auto-generated method stub
		Menu m = menuRepository.findById(menu.getId()).get();
		m.setName(menu.getName());
		m.setIntro(menu.getIntro());
		m.setProfile(menu.getProfile());
		m.setPrice(menu.getPrice());
		m.setMenuType(menu.getMenuType());
		menuRepository.save(m);

	}

	@Override
	public List<Menu> findByMenuType(MenuType menuType) {
		return menuRepository.findByMenuType(menuType);
	}

	@Override
	public List<Menu> findByMenunameLike(String word) {
		word = "%"+word+"%";
		return menuRepository.findByNameLike(word);
		
	}

	

}
