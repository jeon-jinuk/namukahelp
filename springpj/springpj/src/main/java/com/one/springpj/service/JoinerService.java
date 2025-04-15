package com.one.springpj.service;

import java.util.List;

import com.one.springpj.constant.JoinStatus;
import com.one.springpj.model.Joiner;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;

public interface JoinerService {
	public void insert(Joiner joiner);
	public List<Joiner> findJoinUserList(Long id, JoinStatus joinStatus);
	public List<Joiner> findApplyUser(Long leaderId);
	public List<Joiner> findMember(Long studyId);
	
	public int joinCheck(Long id, JoinStatus joinStatus, Long studyId);
	public int joinCount(Study study, JoinStatus joinStatus);
	
	public Joiner findById(Long id);
	public Joiner findByStudyAndUser(Study study, User user);
	public void update(Joiner joiner);
	public void delete(Long id);
}
