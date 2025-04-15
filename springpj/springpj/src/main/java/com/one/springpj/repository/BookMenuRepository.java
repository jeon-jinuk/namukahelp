package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Book;
import com.one.springpj.model.BookMenu;

public interface BookMenuRepository extends JpaRepository<BookMenu, Long>{
	public List<BookMenu> findByBook(Book book);
}
