package com.one.springpj.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.one.springpj.constant.MenuType;
import com.one.springpj.model.Menu;
import com.one.springpj.service.MenuService;

import lombok.extern.java.Log;

@Controller
@Log
@RequestMapping("/")
public class MenuViewController {

	@Autowired
	private MenuService menuService;

	@GetMapping("menuView")
	public void list(Model model, @RequestParam(name="type", defaultValue="") String type) {
		if (type.equals("")==false) {
			MenuType menuType = MenuType.valueOf(type);
			List<Menu> menuList = menuService.findByMenuType(menuType);
			model.addAttribute("list", menuList);
		} 
		else {
			model.addAttribute("list", menuService.menuList());
		}

	}
//	@GetMapping("menuView")
//	public void List(Model model, @RequestParam(name="type", defaultValue="") String type,
//			@RequestParam(name = "field", defaultValue = "") String field,
//			@RequestParam(name = "word", defaultValue = "") String word) {
//		List<Menu> menuList = null;
//		if (type.equals("")==false) {
//			MenuType menuType = MenuType.valueOf(type);
//			menuList = menuService.findByMenuType(menuType);
//			model.addAttribute("list", menuList);		
//		} else 
//			if (!field.equals("") && !word.equals("")) {
//			log.info("=======search====== :" + field + "====" + word);
//			switch (field) {
//			case "name":
//				menuList = menuService.findByMenunameLike(word);
//				break;
//
//			default:
//				break;
//			}
//			} else {
//			menuList = menuService.menuList();
//			
//		}
//		model.addAttribute("list", menuService.menuList());
//	}
	
	
}
