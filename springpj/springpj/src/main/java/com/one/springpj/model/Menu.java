package com.one.springpj.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.one.springpj.constant.MenuType;

import lombok.Data;

@Data
@Entity
public class Menu {
	@Id
	@Column(name="menu_id")
	@GeneratedValue
	private Long id;
	
	private String name;
	private String intro;
	private String profile;
	private int price;
	
	@Enumerated(EnumType.STRING)
	private MenuType menuType;
	
	
	@OneToMany(mappedBy ="menu",
			fetch=FetchType.LAZY,
			cascade = CascadeType.ALL)
	private List<CafeMenu> cafeMenus;
}
