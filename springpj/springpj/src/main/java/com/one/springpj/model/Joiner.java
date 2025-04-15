package com.one.springpj.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.one.springpj.constant.JoinStatus;
import com.one.springpj.constant.StudyRole;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data
@Entity
@Getter @Setter
public class Joiner {

	@Id
	@GeneratedValue
	@Column(name="joiner_id")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="study_id")
	private Study study;
	
	@DateTimeFormat(pattern = "yy.MM.dd")
	@UpdateTimestamp
	private Date regdate;
	
	@Enumerated(EnumType.STRING)
	private StudyRole studyRole;
	
	@Enumerated(EnumType.STRING)
	private JoinStatus joinStatus;
}
