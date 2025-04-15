package com.one.springpj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
@Entity
public class BookSeat {
	@Id
	@Column(name="book_seat_id")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	@ManyToOne
	@JoinColumn(name="seat_id")
	private Seat seat;
	
	
}
