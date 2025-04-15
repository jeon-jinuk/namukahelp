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
public class File {
	@Id
	@Column(name="file_id")
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="board_id")
	private Board board;
	
	private String saveFolder;
	private String originFile;
	private String saveFile;
	private String fileType;
}
