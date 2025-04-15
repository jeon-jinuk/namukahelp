package com.one.springpj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Tag {
	@Id
	@Column(name="tag_id")
	@GeneratedValue
	private Long id;
	
	private String tag;
	
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;

}
