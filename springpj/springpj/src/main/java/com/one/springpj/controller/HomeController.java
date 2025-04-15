package com.one.springpj.controller;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.springpj.model.User;
import com.one.springpj.service.BranchService;
import com.one.springpj.service.StudyService;
import com.one.springpj.service.UserService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/")
@Log
public class HomeController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private StudyService studyService;

	@Autowired
	private BranchService branchService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	

	@GetMapping(value = { "", "index" })
	public String index(Model model) {
		model.addAttribute("studyCount", studyService.countStudy());
		model.addAttribute("userCount", userService.userCount());
		model.addAttribute("branchList", branchService.branchList());
		model.addAttribute("branchCount", branchService.count());
		model.addAttribute("studyList", studyService.getTop3Study());
		return "index";
	}

	@GetMapping("join")
	public void joinForm() {
	}

	@PostMapping("joinProc")
	@ResponseBody
	public String join(@RequestBody User user) {
		if (userService.findByUsername(user.getUsername()) != null) {
			return "fail";
		}
		String rawPassword = user.getPassword();
		String encPassword = bCryptPasswordEncoder.encode(rawPassword);
		user.setPassword(encPassword);
		user.setMileage(0);
		user.setProfile("/images/defaultUser.png");

		userService.register(user);
		return "success";
	}

	@GetMapping("login")
	public void loginForm() {
	}

	@GetMapping("manager")
	@ResponseBody
	public String exManager() {
		log.info("매니저 권한");
		return "manager";
	}

	@GetMapping("user")
	@ResponseBody
	public String exUser() {
		log.info("유저 권한");
		return "user";
	}
	
	@PostMapping("idCheck")
	@ResponseBody
	public String idCheck(String username) {
		User user = userService.findByUsername(username);
		if(user!=null) {
			return "unavailable";
		}return "available";
	}
	
}
