package com.one.springpj.service;

import java.util.Date;
import java.util.List;

import com.one.springpj.model.Book;
import com.one.springpj.model.BookMenu;
import com.one.springpj.model.BookSeat;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Seat;
import com.one.springpj.model.Study;

public interface BookService {
	public List<Book> getBookList();
	public void bookSave(Book book);
	
	public List<Seat> getSeatList();
	public List<Seat> unavailableSeat(Date startdate, Date enddate, Branch branch);
	
	public long countByWeekCount(Date startDate, Date enddate, Branch branch);
	public List<Book> findByToday(Date startDate, Date enddate, Branch branch);
	
	public Seat findSeatByBranchAndName(Branch branch, String name);
	
	public void saveBookMenu(BookMenu bookMenu);
	public BookMenu getBookMenu(Long id);
	public List<BookMenu> getBookMenuList(Long bookId);
	
	public void saveBookSeat(BookSeat bookSeat);
	
	public List<Book> findByStudy(Study study);
	public List<Book> findByBranch(Branch branch);
}
