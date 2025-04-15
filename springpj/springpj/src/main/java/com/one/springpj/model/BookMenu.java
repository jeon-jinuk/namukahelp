package com.one.springpj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.ColumnDefault;

import lombok.Data;

@Data
@Entity
public class BookMenu {
	@Id
	@Column(name="book_menu_id")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="cafe_menu_id")
	private CafeMenu cafeMenu;
	
	@ColumnDefault("0")
	private int count;
	
	@ColumnDefault("0")
	private int totalPrice;
	
	
}
