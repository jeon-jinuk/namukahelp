package com.one.springpj.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.one.springpj.constant.FileMaker;
import com.one.springpj.constant.Role;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Menu;
import com.one.springpj.model.Seat;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;
import com.one.springpj.service.BranchService;
import com.one.springpj.service.MenuService;
import com.one.springpj.service.StudyService;
import com.one.springpj.service.UserService;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/admin/")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private BranchService branchService;

	@Autowired
	private StudyService studyService;

//	@Autowired
//	private StudyGroupService studyGroupService;

	@Autowired
	private MenuService menuService;

	@GetMapping("admin")
	public void adminPage() {
	}

	// -----------------------------지점(branch)
	// 컨트롤------------------------------------

	// branchList 입장+List불러오기
	@GetMapping("branch/branchList")
	public void list(Model model) {
		model.addAttribute("list", branchService.branchList());
	}

	// branchRegister 입장
	@GetMapping("branch/branchRegister")
	public void insertForm(Branch branch) {
	}

	// branch추가
	@PostMapping("branch/insert")
	public String insert(Branch branch, String username, MultipartFile file, HttpSession session, String[] seatNum) {
		// 알파벳 배열
		char[] clist = new char[26];
		for (int i = 0; i < 26; i++) {
			clist[i] = (char) (65 + i);
		}

		User user = userService.findByUsername(username);
		String imagePath = FileMaker.save(file, session);
		branch.setManager(user);
		branch.setProfile(imagePath);

		branchService.insert(branch);

		// 좌석 데이터 저장
		List<Seat> seatList = new ArrayList<Seat>();
		for (String s : seatNum) {
			Seat seat = new Seat();
			String[] arr = s.split(",");

			seat.setBranch(branch);
			seat.setName(clist[Integer.parseInt(arr[0])] + arr[1]);
			seat.setX(Integer.parseInt(arr[0]));
			seat.setY(Integer.parseInt(arr[1]));
			branchService.insertSeat(seat);
			seatList.add(seat);
		}
//			branch.setSeats(seatList);

		return "redirect:/admin/branch/branchList";
	}

	// id값을 가지고 branchUpdate 입장
	@GetMapping("/branch/update/{id}")
	public String updateForm(@PathVariable("id") Long id, Model model) {
		Branch branch = branchService.findById(id);
		model.addAttribute("branch", branch);
		model.addAttribute("seatList", branchService.findSeatByBranch(branch));
		return "/admin/branch/branchUpdate";
	}

	// 수정한 값 보내기
	@PostMapping("/branch/update")
	public String update(Branch branch, MultipartFile file, HttpSession session, String[] seatNum, String username) {
		// 파일 저장
		log.info("==================" + file);
		if (file != null) {
			String imagePath = FileMaker.save(file, session);
			branch.setProfile(imagePath);
		}
		branch.setManager(userService.findByUsername(username));

		// 지점 업데이트
		branchService.update(branch);

		// 기존 자리 삭제
		List<Seat> deleteSeatList = branchService.findSeatByBranch(branch);
		branchService.deleteSeat(deleteSeatList);

		// 알파벳 배열
		char[] clist = new char[26];
		for (int i = 0; i < 26; i++) {
			clist[i] = (char) (65 + i);
		}
		// 좌석 데이터 저장
		List<Seat> seatList = new ArrayList<Seat>();
		for (String s : seatNum) {
			Seat seat = new Seat();
			String[] arr = s.split(",");

			seat.setBranch(branch);
			seat.setName(clist[Integer.parseInt(arr[0])] + arr[1]);
			seat.setX(Integer.parseInt(arr[0]));
			seat.setY(Integer.parseInt(arr[1]));
			branchService.insertSeat(seat);
			seatList.add(seat);
		}

		return "redirect:/admin/branch/branchList";
	}

	// 삭제
	@GetMapping("/branch/delete/{id}")
	public String branchDelete(@PathVariable("id") Long id) {
		branchService.delete(id);
		return "redirect:/admin/branch/branchList";
	}

	// ===========================================================================

	// -----------------------메뉴(menu)컨트롤--------------------------------------

	// menuList 입장+List 불러오기
	@GetMapping("menu/menuList")
	public void menuList(Model model) {
		model.addAttribute("list", menuService.menuList());
	}

	// menuRegister 입장
	@GetMapping("menu/menuRegister")
	public void insertForm(Menu menu) {
	}

	// menuRegister의 submit타입 버튼에 의해 form action명 insert로 post된거 처리
	@PostMapping("menu/insert")
	public String insert(Menu menu, MultipartFile file, HttpSession session) {
		String imagePath = FileMaker.save(file, session);
		menu.setProfile(imagePath);
		menuService.insert(menu);
		return "redirect:/admin/menu/menuList";
	}

	// menuUpdate로 아이디들고 들어가기
	@GetMapping("/menu/menuUpdate/{id}")
	public String menuupdateForm(@PathVariable("id") Long id, Model model) {
		Menu menu = menuService.findById(id);
		model.addAttribute("menu", menu);
		return "/admin/menu/menuUpdate";
	}

	// 수정
	@PostMapping("/menu/menuUpdate")
	public String menuupdate(Menu menu, MultipartFile file, HttpSession session) {
		String imagePath = FileMaker.save(file, session);
		menu.setProfile(imagePath);
		menuService.update(menu);
		return "redirect:/admin/menu/menuList";
	}

	// 삭제
	@GetMapping("menu/delete/{id}")
	public String menuDelete(@PathVariable("id") Long id) {
		menuService.delete(id);
		return "redirect:/admin/menu/menuList";
	}

	// ==========================================================================

	// ----------------------마일리지(mileage)컨트롤------------------------------

	@GetMapping("mileage/mileageList")
	public void mileagelist(Model model) {
		model.addAttribute("userlist", userService.getUserlist());

	}

	// ==========================================================================

	@PostMapping("/addmile")
	@ResponseBody
	public void addmileage(int mile, String username) {
		log.info("mile:" + mile + "username:" + username);
		User user = userService.findByUsername(username);
		user.setMileage(user.getMileage() + mile);
		userService.update(user);
	}

	@PostMapping("/delmile")
	@ResponseBody
	public void delmileage(int mile, String username) {
		log.info("mile:" + mile + "username:" + username);
		User user = userService.findByUsername(username);
		user.setMileage(user.getMileage() - mile);
		userService.update(user);
	}

	// ==========================================================================

	// ==========================================================================

	// -----------------------스터디(study)컨트롤---------------------------------

	// studyList 입장+List
	@GetMapping("/study/studyList")
	public void studyList(Model model) {
		List<Study> studies = studyService.getList();
		model.addAttribute("list", studies);
	}

	@GetMapping("/study/delete/{id}")
	public String delete(@RequestParam(name = "id", defaultValue = "")@PathVariable("id") Long id) {
		studyService.delete(id);
		return "redirect:/admin/study/studyList";
	}

	// ============================================================================

	// ---------------------------유저(user) 컨트롤----------------------------------

	@GetMapping("user/userList")
	public void userList(Model model, @RequestParam(name = "role", defaultValue = "") String role,
			@RequestParam(name = "field", defaultValue = "") String field,
			@RequestParam(name = "word", defaultValue = "") String word) {
		List<User> userList = null;
		if (!field.equals("") && !word.equals("")) {
			log.info("=======search====== :" + field + "====" + word);
			switch (field) {
			case "username":
				userList = userService.findByUsernameLike(word);
				break;
			case "nick":
				userList = userService.findByNickLike(word);
				break;
			case "addr":
				userList = userService.findByAddrLike(word);
				break;
			case "email":
				userList = userService.findByEmailLike(word);
				break;
			default:
				break;
			}
			log.info(">>>>>>>" + userList.toString());
		} else if (!role.equals("")) {
			log.info("=======findRole====== :" + role);
			switch (role) {
			case "manager":
				userList = userService.findByRole(Role.ROLE_MANAGER);
				break;
			case "user":
				userList = userService.findByRole(Role.ROLE_USER);
				break;

			default:
				break;
			}
		} else {
			userList = userService.getUserlist();
		}
		model.addAttribute("userlist", userList);
	}

	// ===============================================================================

	@PostMapping("userdelete")
	public String delete(Long[] delete) {
		if (delete != null) {
			for (Long id : delete) {
				log.info("===============userId:"+id+"=========");
				userService.delete(id);
			}
		}
		return "redirect:/admin/user/userList";
	}

	// ===============================================================================

}