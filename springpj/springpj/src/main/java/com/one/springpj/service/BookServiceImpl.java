package com.one.springpj.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.one.springpj.model.Book;
import com.one.springpj.model.BookMenu;
import com.one.springpj.model.BookSeat;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Seat;
import com.one.springpj.model.Study;
import com.one.springpj.repository.BookMenuRepository;
import com.one.springpj.repository.BookRepository;
import com.one.springpj.repository.BookSeatRepository;
import com.one.springpj.repository.SeatRepository;

import lombok.extern.java.Log;

@Service
@Log
public class BookServiceImpl implements BookService {
	@Autowired
	private BookSeatRepository bookSeatRepository;
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private SeatRepository seatRepository;
	@Autowired
	private BookMenuRepository bookMenuRepository;
	
	@Override
	public void bookSave(Book book) {
		bookRepository.save(book);
	}
	@Override
	public List<Seat> unavailableSeat(Date startdate, Date enddate, Branch branch) {
		List<Seat> seatList = new ArrayList<Seat>();
		List<Book> bookList = bookRepository.findByBookDateBetweenAndBranch(startdate, enddate, branch);
		for (Book b : bookList) {
			List<BookSeat> bookSeatList = bookSeatRepository.findByBook(b);
			for (BookSeat bs : bookSeatList) {
				Seat seat = bs.getSeat();
				seatList.add(seat);
			}
		}
		
		return seatList;
	}
	
	@Override
	public Seat findSeatByBranchAndName(Branch branch, String name) {
		return seatRepository.findByBranchAndName(branch, name);
	}

	@Override
	public List<Book> getBookList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Seat> getSeatList() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	@Override
	public void saveBookMenu(BookMenu bookMenu) {
		bookMenuRepository.save(bookMenu);
	}
	@Override
	public BookMenu getBookMenu(Long id) {
		return bookMenuRepository.findById(id).get();
	}
	@Override
	public List<BookMenu> getBookMenuList(Long bookId){
		return bookMenuRepository.findByBook(bookRepository.getById(bookId));
	}
	@Override
	public void saveBookSeat(BookSeat bookSeat) {
		bookSeatRepository.save(bookSeat);
		
	}
	@Override
	public List<Book> findByStudy(Study study) {
		Sort sort = sortByBookDate();
		return bookRepository.findByStudy(study, sort);
	}
	
	@Override
	public List<Book> findByBranch(Branch branch) {
		Sort sort = sortByBookDate();
		return bookRepository.findByBranch(branch,sort);
	}
	
	public Sort sortByBookDate() {
		return Sort.by(Sort.Direction.DESC, "bookDate");
	}
	@Override
	public long countByWeekCount(Date startDate, Date enddate, Branch branch) {
		return bookRepository.countByBookDateBetweenAndBranch(startDate, enddate, branch);
	}
	@Override
	public List<Book> findByToday(Date startDate, Date enddate, Branch branch) {
		return bookRepository.findByBookDateBetweenAndBranch(startDate, enddate, branch);
	}
}
