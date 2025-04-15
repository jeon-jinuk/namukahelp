package com.one.springpj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.one.springpj.constant.MenuStatus;

import lombok.Data;

@Data
@Entity
public class CafeMenu {
	@Id
	@GeneratedValue
	@Column(name="cafe_menu_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="menu_id")
	private Menu menu;
	
	@Enumerated(EnumType.STRING)
	private MenuStatus menuStatus;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	
}	
