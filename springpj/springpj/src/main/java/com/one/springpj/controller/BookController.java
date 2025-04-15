package com.one.springpj.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.one.springpj.constant.BookStatus;
import com.one.springpj.constant.JoinStatus;
import com.one.springpj.model.Book;
import com.one.springpj.model.BookMenu;
import com.one.springpj.model.BookSeat;
import com.one.springpj.model.Branch;
import com.one.springpj.model.CafeMenu;
import com.one.springpj.model.Joiner;
import com.one.springpj.model.Seat;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;
import com.one.springpj.service.BookService;
import com.one.springpj.service.BranchService;
import com.one.springpj.service.JoinerService;
import com.one.springpj.service.StudyService;
import com.one.springpj.service.UserService;

import lombok.extern.java.Log;

@Controller
@RequestMapping("/book/")
@Log
public class BookController {

	@Autowired
	private BranchService branchService;
	@Autowired
	private UserService userService;
	@Autowired
	private JoinerService joinerService;
	@Autowired
	private BookService bookService;
	@Autowired
	private StudyService studyService;
	

	@GetMapping("bookCafe")
	public void list(Model model) {
		model.addAttribute("list", branchService.branchList());

	}

	@GetMapping("bookSeat")
	public String bookSeatForm(Long id, Model model, Principal principal, String dateTime) {
		if (principal == null) {
			return "/index";
		}
		List<Seat> seatList = branchService.findByBranchId(id);
		User user = userService.findByUsername(principal.getName());
		List<Joiner> joinerList = joinerService.findJoinUserList(user.getId(), JoinStatus.ACCEPT);

		if (seatList != null) {
			model.addAttribute("seats", seatList);
			model.addAttribute("joinerList", joinerList);
			model.addAttribute("cafeId", id);
			model.addAttribute("dateTime", dateTime);
		}

		return "/book/bookSeat";
	}

	@PostMapping(value = "bookSeat")
	@ResponseBody
	public ResponseEntity<List<Seat>> bookSeatTime(String dateTime, Long branchId) throws ParseException {
		log.info("==================" + dateTime);
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Calendar cal = Calendar.getInstance();
		Date date = sdformat.parse(dateTime);
		cal.setTime(date);
		cal.add(Calendar.HOUR, -1);
		String startdate_str = sdformat.format(cal.getTime());
		Date startdate = sdformat.parse(startdate_str);

		cal.add(Calendar.HOUR, 2);
		String enddate_str = sdformat.format(cal.getTime());
		Date enddate = sdformat.parse(enddate_str);
		log.info("==================" + startdate + "====" + enddate);
		List<Seat> seatList = bookService.unavailableSeat(startdate, enddate, branchService.findById(branchId));
		log.info("========" + seatList);
		return new ResponseEntity<List<Seat>>(seatList, HttpStatus.OK);
	}

	@GetMapping("bookMenu")
	public void bookMenuForm(Long studyId, Long cafeId, String[] seat, Model model, String dateTime) {
		Branch branch = branchService.findById(cafeId);
		List<CafeMenu> cafeMenuList = branchService.cafeMenufindByBranchId(branch);
		model.addAttribute("cafeMenus", cafeMenuList);
		model.addAttribute("seatList", seat);
		model.addAttribute("studyId", studyId);
		model.addAttribute("cafeId", cafeId);
		model.addAttribute("dateTime", dateTime);
	}

	@PostMapping("bookConfirm")
	public void bookConfirm(Long studyId, //
			Principal principal, //
			Long cafeId, //
			String[] seat, //
			String bookdate,
			Long[] cafeMenuId, //
			int[] count, //
			int[] menuTotal,//
			int totalPrice,//
			int useMile,//
			int userMile,
			int studyMile,
			Model model) throws ParseException {
		
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = sdformat.parse(bookdate);
		Branch branch = branchService.findById(cafeId);
		
		User user = userService.findByUsername(principal.getName());
		user.setMileage(user.getMileage()-useMile+userMile);
		userService.update(user);
		
		Study study = studyService.read(studyId);
		study.setMileage(study.getMileage()+studyMile);
		studyService.update(study);
		
		Book book = new Book();
		book.setBookDate(date);
		book.setBooker(user);
		book.setBranch(branch);
		book.setStudy(study);
		book.setBookStatus(BookStatus.BOOKED);
		book.setTotal(totalPrice);
		
		
		bookService.bookSave(book);
		
		List<BookSeat> bookSeatList = new ArrayList<BookSeat>();
		for(int i=0; i<seat.length; i++) {
			BookSeat bookSeat= new BookSeat();
			bookSeat.setBook(book);
			bookSeat.setSeat(bookService.findSeatByBranchAndName(branch, seat[i]));
			bookSeatList.add(bookSeat);
			bookService.saveBookSeat(bookSeat);
		}
		List<BookMenu> bookMenuList = new ArrayList<BookMenu>();
		
		for (int i=0; i<cafeMenuId.length; i++) {
			BookMenu bookMenu = new BookMenu();
			CafeMenu cafeMenu =branchService.cafeMenuFindById(cafeMenuId[i]);
			bookMenu.setCafeMenu(cafeMenu);
			bookMenu.setCount(count[i]);
			bookMenu.setTotalPrice(menuTotal[i]);
			bookMenu.setBook(book);
			bookMenuList.add(bookMenu);
			bookService.saveBookMenu(bookMenu);
		}

		model.addAttribute("book", book);
		model.addAttribute("bookSeatList",bookSeatList);
		model.addAttribute("bookMenuList",bookMenuList);
	}
	
	@PostMapping("bookAll")
	@ResponseBody
	public String bookAll(Book book, List<BookSeat> bookSeatList , List<BookMenu> bookMenuList, int total) {
		log.info(book.toString());
		log.info(bookSeatList.toString());
		log.info(bookMenuList.toString());
		log.info(total+"end");
		return "success";
	}
}
