package com.one.springpj.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.one.springpj.constant.JoinStatus;
import com.one.springpj.model.Book;
import com.one.springpj.model.BookSeat;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Joiner;
import com.one.springpj.model.Menu;
import com.one.springpj.model.Seat;
import com.one.springpj.model.Study;
import com.one.springpj.repository.BookRepository;
import com.one.springpj.repository.BookSeatRepository;
import com.one.springpj.repository.BranchRepository;
import com.one.springpj.repository.JoinerRepository;
import com.one.springpj.repository.MenuRepository;
import com.one.springpj.repository.SeatRepository;
import com.one.springpj.repository.StudyRepository;

import lombok.extern.java.Log;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@TestPropertySource(locations = "classpath:application.properties")
@Log
public class StudyTest {
	@Autowired
	JoinerRepository joinerRepository;

	@Autowired
	SeatRepository seatRepository;

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookSeatRepository bookSeatRepository;

	@Autowired
	BranchRepository branchRepository;

	@Autowired
	StudyRepository studyRepository;

	@Autowired
	MenuRepository menuRepository;

//	@Test
	void load() {
//		studyRepository.findByJoinUser((long) 1);
//		System.out.println(joinerRepository.findJoinUserList((long)1));
		List<Joiner> list = joinerRepository.findAll();
		for (Joiner l : list) {
			System.out.println(l);
		}
		System.out.println();
	}

//	@Test
	public void getSeatList() throws ParseException {
		String date_str = "2022.3.15 10:00";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date startdate = sdformat.parse(date_str);
//		
		Calendar cal = Calendar.getInstance();
		cal.setTime(startdate);
		cal.add(Calendar.HOUR, 2);
//		
		String end_str = sdformat.format(cal.getTime());
		Date enddate = sdformat.parse(end_str);
//		
//		System.out.println(startdate.toString() + "====="+enddate.toString());

//		LocalDateTime startDateTime = LocalDateTime.of(2022, 03, 15, 11, 00);
//		LocalDateTime endDateTime = LocalDateTime.of(2022, 03, 15, 13, 00);
//		
//		System.out.println("===================="+startDateTime+"====="+endDateTime);
//		List<Seat> seatList = seatRepository.findByBookDate("2022.03.15 11:00","2022.03.15 13:00");
//		List<Book> bookList = bookRepository.findByBookDate("2022.03.15 11:00","2022.03.15 13:00");
//		List<Book> bookList = bookRepository.findByBookDate(startDateTime,endDateTime);

//		List<Seat> seatList = seatRepository.findByBookDate((long)5);
		Branch branch = branchRepository.findById((long) 5).get();
		List<Book> booklist = bookRepository.findByBookDateBetweenAndBranch(startdate, enddate, branch);
		System.out.println("==============================" + booklist.get(0).getBranch());
	}

//	@Test
	public void test3() {
		List<BookSeat> list = bookSeatRepository.findAll();
		System.out.println(list.toString());
		for (BookSeat bs : list) {
			System.out.println(bs.getBook().getBookDate());
			System.out.println(bs.getSeat().getName());
		}
	}

//	@Test
	public void test4() throws ParseException {

		String date_str = "2022.4.15 10:00";
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		Date startdate = sdformat.parse(date_str);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startdate);
		cal.add(Calendar.HOUR, 2);

		String end_str = sdformat.format(cal.getTime());
		Date enddate = sdformat.parse(end_str);

		Branch branch = branchRepository.findById((long) 5).get();
		List<Seat> seatList = new ArrayList<Seat>();
		List<Book> bookList = bookRepository.findByBookDateBetweenAndBranch(startdate, enddate, branch);
		for (Book b : bookList) {
			List<BookSeat> bookSeatList = bookSeatRepository.findByBook(b);
			for (BookSeat bs : bookSeatList) {
				Seat seat = bs.getSeat();
				seatList.add(seat);
			}
		}

		for (Seat seat : seatList) {
			System.out.println("============================" + seat.getName());
		}
	}

//	@Test
	public void test5() {
		Study study = studyRepository.findById((long) 120).get();
		int count = joinerRepository.joinCount(study.getId(), JoinStatus.ACCEPT.toString());
		System.out.println(count);
	}

//	@Test
	public void test6() {
		Branch branch = branchRepository.findById((long) 79).get();
		List<Menu> list = menuRepository.findbyMenuNotCafeMenu(branch.getId());
//		System.out.println(">>>>>>>>>>>>>>>>>>"+list.toString());
		for (Menu menu : list) {
			System.out.println(">>>>>>>>>>>>>>>>>>" + menu.getName());
		}

	}

//	@Test
	public void test7() {
//		List<Study> studyList = studyRepository.findByTitleLike("%룹%");
//		System.out.println(">>>>>>find : " + studyList.toString());
//		for (Study study : studyList) {
//			System.out.println(">>>>>>>>>>>>>>" + study.getTitle());
//		}
//
//		List<Study> studyList2 = studyRepository.findByTitleLike("%그룹룹%");
//		System.out.println(">>>>>>find : " + studyList2.toString());
//		for (Study study : studyList2) {
//			System.out.println(">>>>>>>>>>>>>>" + study.getTitle());
//		}
	}

	@Test
	public void test8() throws ParseException {
		String[] week = {"SUN","MON", "TUE", "WED", "THU", "FRI", "SAT"};
		SimpleDateFormat sdformat = new SimpleDateFormat("yyyy.MM.dd HH:mm");		
		
		Date today = new Date();
		today.setHours(0);
		today.setMinutes(0);
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -3);
		
		for(int i=0; i<7; i++) {
			cal.add(Calendar.HOUR_OF_DAY, +24);
			String end_str = sdformat.format(cal.getTime());
			Date enddate = sdformat.parse(end_str);
			
			cal.add(Calendar.HOUR_OF_DAY, -24);
			String start_str = sdformat.format(cal.getTime());
			Date startdate = sdformat.parse(start_str);
			
			int dayIndex = cal.getTime().getDay();
			
			
			log.info(">>>>day :" + startdate+">>>"+enddate+">>>"+week[dayIndex]);
			cal.add(Calendar.DATE, 1);
		}
	}
}
