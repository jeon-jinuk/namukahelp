package com.one.springpj.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.one.springpj.model.Board;
import com.one.springpj.model.Likes;
import com.one.springpj.model.Reply;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;

public interface StudyService {
	public List<Study> getList();
	public void insert(Study study);
	public Study read(Long id);
	public void update(Study study);
	public List<Study> findbyLeader(User user);
	public long countStudy();
	public List<Study> getTop3Study();
	
	public List<Likes> getLikes();
	public void insertLike(Likes like);
	public Likes isLike(Long studyId, Long userId);
	public void deleteLike(Long id);
	
	
	public List<Board> findByStudyId(Long id);
	
	public void insertBoard(Board board);

	public List<Study> findByStudynameLike(String word, Pageable pageable);
//	public List<Study> findByLeaderLike(String word); 

	public Board findBoardById (Long id);
	public void deleteBoard(Long id);
	
	public void insertReply(Reply reply);
	public void deleteReply(Long id);
	public List<Reply> findReplyByBoard(Long id);
	public int replyCountbyBoard(Board board);
	public List<Study> paging(Pageable pageable);
	public List<Study> getList(Pageable pageable);
	public void delete(Long id);
}
