package com.one.springpj.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;
import org.springframework.format.annotation.DateTimeFormat;

import com.one.springpj.constant.BookStatus;

import lombok.Data;

@Data
@Entity
public class Book {
	@Id
	@Column(name="book_id")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="booker_id")
	private User booker;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	
	
	@ManyToOne
	@JoinColumn(name="study_id")
	private Study study;
	
	@DateTimeFormat(pattern = "yy.MM.dd HH:MM")
	private Date bookDate;
	
	@Enumerated(EnumType.STRING)
	private BookStatus bookStatus;
	
	@OneToMany(mappedBy = "book",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<BookMenu> bookMenus;
	
	@OneToMany(mappedBy = "book",
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<BookSeat> bookseats;
	
	private int total;
}
