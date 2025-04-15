package com.one.springpj.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Book;
import com.one.springpj.model.Branch;
import com.one.springpj.model.Study;

public interface BookRepository extends JpaRepository<Book, Long>{
	
	public List<Book> findByBookDateBetweenAndBranch(Date startdate, Date enddate,Branch branch);
	public long countByBookDateBetweenAndBranch(Date startdate, Date enddate,Branch branch);
	public List<Book> findByStudy(Study study,Sort sort);
	public List<Book> findByBranch(Branch branch, Sort sort);
}
