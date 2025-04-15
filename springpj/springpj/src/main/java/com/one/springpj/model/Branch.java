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
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Branch {
	@Id
	@Column(name= "branch_id")
	@GeneratedValue
	private Long id;
		
	@ManyToOne
	@JoinColumn(name="local_id")
	private Local local;
	
	private String name;
	private String addr;
	private String phone;
	
	@ManyToOne
	@JoinColumn(name="manager_id")
	private User manager;
	
	private String profile;
	
	@OneToMany(mappedBy = "branch",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<Seat> seats;
	
	@OneToMany(mappedBy = "branch",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private List<CafeMenu> cafeMenus;
}
