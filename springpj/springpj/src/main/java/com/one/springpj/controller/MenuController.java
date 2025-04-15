package com.one.springpj.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.one.springpj.constant.FileMaker;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Menu;
import com.one.springpj.service.BranchService;
import com.one.springpj.service.MenuService;

@Controller
@RequestMapping("/admin/menu/")
public class MenuController {
	
//	@Autowired
//	private MenuService menuService;
//	
//	@GetMapping("menuList")
//	public void list(Model model) {
//		model.addAttribute("list", menuService.menuList());
//
//	}
//	
////	register.jsp파일을 열기위해 필요한거
//	@GetMapping("menuRegister")
//	public void insertForm(Menu menu) {
//		
//	}
////	@GetMapping("menuUpdate")
////	public void updateForm(Menu menu) {
////		
////	}
//	
//	
//	@GetMapping("menuUpdate/{id}")
//	public String updateForm(@PathVariable("id") Long id, Model model) {
//		Menu menu = menuService.findById(id);
//		model.addAttribute("menu", menu);
//		return "/admin/menu/menuUpdate";
//	}
//	@PostMapping("menuUpdate")
//	public String update(Menu menu,MultipartFile file, HttpSession session) {
//		String imagePath = FileMaker.save(file, session);
//		menu.setProfile(imagePath);
//		menuService.update(menu);
//		return "redirect:/admin/menu/menuList";
//	}
//	
////	register.jsp의 submit타입 버튼에 의해 form action명 insert로 post된거 처리
//	@PostMapping("insert")
//	public String insert(Menu menu,MultipartFile file, HttpSession session) {
//		String imagePath = FileMaker.save(file, session);
//		menu.setProfile(imagePath);
//		menuService.insert(menu);
//		return "redirect:/admin/menuManagement";
//	}
//	
//	@GetMapping("delete/{id}")
//	public String delete(@PathVariable("id")Long id) {
//		menuService.delete(id);
//		return "redirect:/admin/menuManagement";
//	}
}