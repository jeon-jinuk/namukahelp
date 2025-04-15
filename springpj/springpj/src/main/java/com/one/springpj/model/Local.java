package com.one.springpj.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Local {
	@Id
	@GeneratedValue
	@Column(name="local_id")
	private Long id;
	
	
	private String dept1; //ex_부산
	private String dept2; //ex_부산진구
}
