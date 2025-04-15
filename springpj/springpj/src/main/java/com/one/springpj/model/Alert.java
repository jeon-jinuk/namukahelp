package com.one.springpj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
@Entity
public class Alert {
	@Id
	@GeneratedValue
	@Column(name = "board_id")
	private Long id;

	private Long userId;
	
	private String message;
	
	@CreationTimestamp
	@DateTimeFormat(pattern = "yy-MM-dd")
	private Date regdate;
}
