package com.one.springpj.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.one.springpj.constant.FileMaker;
import com.one.springpj.constant.JoinStatus;
import com.one.springpj.constant.StudyRole;
import com.one.springpj.model.Alert;
import com.one.springpj.model.Joiner;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;
import com.one.springpj.service.JoinerService;
import com.one.springpj.service.StudyService;
import com.one.springpj.service.UserService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/user/")
@Log
public class UserController {
	@Autowired
	JoinerService joinerService;
	@Autowired
	UserService userService;
	@Autowired
	StudyService studyService;

	@GetMapping("user")
	public void userForm() {
	}

	// -------------------------내정보-----------------------------------

	@GetMapping("myPage")
	public void userlist(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		model.addAttribute(user);
	}

	@GetMapping("myPageUpdate/{username}")
	public String myPageupdateForm(@PathVariable("username") String username, Model model) {
		User user = userService.findByUsername(username);
		model.addAttribute("user", user);
		return "/user/myPageUpdate";
	}

	// 수정
	@PostMapping("/myPageUpdate")
	public String menuupdate(User user, MultipartFile file, HttpSession session) {
		String imagePath = FileMaker.save(file, session);
		user.setProfile(imagePath);
		userService.update(user);
		return "redirect:/user/myPage";
	}

	@GetMapping("userDel22/{username}")
	@ResponseBody
	public String userDel(@PathVariable("username") String username) {
		User user = userService.findByUsername(username);
		userService.delete(user.getId());
		return "/user/myPageUpdate";
	}

	// =========================================================

	// ------------------------내스터디------------------------------------

	@GetMapping("myStudy")
	public void myStudyForm(Principal principal, Model model) {
		User user = userService.findByUsername(principal.getName());
		List<Joiner> joinerList = joinerService.findJoinUserList(user.getId(), JoinStatus.ACCEPT);
		List<Joiner> waitingList = joinerService.findApplyUser(user.getId());
		List<Study> myStudies = studyService.findbyLeader(user);

		model.addAttribute("joinerList", joinerList);
		model.addAttribute("waitingList", waitingList);
		model.addAttribute("myStudies", myStudies);
	}

	@GetMapping("joiner")
	public void joiner(Model model, Principal principal) {
		User user = userService.findByUsername(principal.getName());
		List<Joiner> applyerList = joinerService.findApplyUser(user.getId());
		List<Joiner> joinedList = joinerService.findJoinUserList(user.getId(), JoinStatus.ACCEPT);

		model.addAttribute("applyerList", applyerList);
		model.addAttribute("joinedList", joinedList);
	}

	@PostMapping("joinerAlert")
	@ResponseBody
	public Object joinerAlert(String username) {
		User user = userService.findByUsername(username);
		List<Joiner> applyerList = joinerService.findApplyUser(user.getId());
		List<Joiner> joinedList = joinerService.findJoinUserList(user.getId(), JoinStatus.ACCEPT);
		return applyerList;
	}

	@PostMapping("joinerDecline")
	@ResponseBody
	public void joinerDecline(Long id) {
		Joiner joiner = joinerService.findById(id);
		joiner.setJoinStatus(JoinStatus.DECLINE);
		joiner.setStudyRole(null);
		joinerService.update(joiner);

		// 메시지 보내기
		User user = joiner.getUser();
		Study study = joiner.getStudy();
		Alert alert = new Alert();
		alert.setMessage(study.getTitle() + "+에서 " + user.getNick() + "님의 가입을 거절했습니다.");
		alert.setUserId(user.getId());
		userService.saveAlert(alert);
	}

	@PostMapping("joinerAccept")
	@ResponseBody
	public void joinerAccept(Long id) {
		Joiner joiner = joinerService.findById(id);
		joiner.setJoinStatus(JoinStatus.ACCEPT);
		joiner.setStudyRole(StudyRole.MEMBER);
		joinerService.update(joiner);

		// 메시지 보내기
		User user = joiner.getUser();
		Study study = joiner.getStudy();
		Alert alert = new Alert();
		alert.setMessage(study.getTitle() + "+에서 " + user.getNick() + "님의 가입을 승인했습니다.");
		alert.setUserId(user.getId());
		userService.saveAlert(alert);
	}

	// =================================================================

	// -------------------------내마일리지---------------------------------

	@GetMapping("myMileage")
	public void myMileageForm() {
	}

	// =============================================================
	// ------------------------스터디 장 페이지--------------------------

	@GetMapping("myLeaderStudy/{id}") // 스터디아이디
	public String myLeaderStudy(@PathVariable("id") Long id, Model model, Principal principal) {
		Study study = studyService.read(id);
		User user = userService.findByUsername(principal.getName());
		List<Joiner> members = joinerService.findMember(id);
		List<Study> myStudies = studyService.findbyLeader(user);
		model.addAttribute("study", study);
		model.addAttribute("members", members);
		model.addAttribute("myStudies", myStudies);
		return "/user/myLeaderStudy";
	}
	
	// 회원 강퇴
	@PostMapping("joinerOut")
	@ResponseBody
	public void outJoiner(Long id) { //조이너 아이디
		
		Joiner joiner = joinerService.findById(id);
		
		// 메시지 보내기
		Study study = joiner.getStudy();
		User user = joiner.getUser();
		Alert alert = new Alert();
		alert.setMessage(study.getTitle() + "+에서 " + user.getNick() + "님을 강퇴했습니다.");
		alert.setUserId(user.getId());
		userService.saveAlert(alert);
		
		joinerService.delete(id);
	}


	// =============================================================
	// ----------------------------알림창-----------------------------

	@GetMapping("getAlert/{id}")
	@ResponseBody
	public List<Alert> getAlert(@PathVariable("id") Long id) {
//		User user = userService.findByUsername(principal.getName());
		return userService.findAlertByUserId(id);
	}
	
	@GetMapping("delAlert/{id}")
	@ResponseBody
	public void delAlert(@PathVariable("id") Long id) {
		userService.deleteAlert(id);
	}


//	@GetMapping("register")
//	public void registerForm() {
//		
//	}
//
//
//	@RequestMapping("userpage")
//	public String userlist(Model model, Principal principal) {
//		User user = userService.findByUsername(principal.getName());
//		model.addAttribute("user", user);
//		return "user/userpage";
//	}
//
//	@RequestMapping("usermileage")
//	public String usermileage(Model model, Principal principal) {
//		User user = userService.findByUsername(principal.getName());
//		model.addAttribute("user", user);
//		return "user/usermileage";
//	}
//
//	
//	@GetMapping("userupdate")
//	public String userupdateForm(Model model, Principal principal) {
//		User user = userService.findByUsername(principal.getName());
//		model.addAttribute("user", user);
//		user.getAddr();
//		user.getEmail();
//		user.getNick();
//		user.getPassword();
//		user.getUsername();
//		userService.userupdate(user, principal);
//		return "user/userupdate";
//	}
//	
//	@PostMapping("userupdate")
//	public String userupdate(User user) {
//		User reguser = userService.findByUsername(user.getUsername());
//		reguser.setAddr(user.getAddr());
//		reguser.setEmail(user.getEmail());
//		reguser.setNick(user.getNick());
//		userService.update(reguser);
//		return "user/userpage";
//	}
//	
//	
//	@PostMapping("userdelete")
//	@ResponseBody
//	public String userdelete(String username) {
//		User user = userService.findByUsername(username);
//		userService.delete(user.getId());
//		return "success";
//	}

}
