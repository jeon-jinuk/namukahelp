package com.one.springpj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Seat {
	@Id
	@GeneratedValue
	@Column(name = "seat_id")
	private Long id;

	private String name;

	@ManyToOne
	@JoinColumn(name = "branch_id")
	@JsonIgnore
	private Branch branch;

//	@ManyToOne
//	@JoinColumn(name="book_id")
//	private Book book;

	@OneToMany(mappedBy = "seat", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonIgnore
	private List<BookSeat> bookseats;

	private int x;
	private int y;
}
