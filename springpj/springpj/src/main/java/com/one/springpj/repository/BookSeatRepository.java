package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Book;
import com.one.springpj.model.BookSeat;
import com.one.springpj.model.Seat;

public interface BookSeatRepository extends JpaRepository<BookSeat, Long>{
	public List<BookSeat> findByBook(Book book);
	public List<BookSeat> findBySeat(Seat seat);
}
