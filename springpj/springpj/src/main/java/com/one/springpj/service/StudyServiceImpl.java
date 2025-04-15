package com.one.springpj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.one.springpj.model.Board;
import com.one.springpj.model.Likes;
import com.one.springpj.model.Reply;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;
import com.one.springpj.repository.BoardRepository;
import com.one.springpj.repository.LikesRepository;
import com.one.springpj.repository.ReplyRepository;
import com.one.springpj.repository.StudyRepository;

import lombok.extern.java.Log;

@Service
@Log
public class StudyServiceImpl implements StudyService {

	@Autowired
	StudyRepository studyRepository;
	@Autowired
	LikesRepository likeRepository;
	@Autowired
	BoardRepository boardRepository;
	@Autowired
	ReplyRepository replyRepository;
	
	@Override
	public void insert(Study study) {
		studyRepository.save(study);
	}
	
	@Override
	public void update(Study study) {
		studyRepository.save(study);
	}

	@Override
	public List<Study> getList() {
		Sort sort = Sort.by(Sort.Direction.DESC, "likes");
		return studyRepository.findAll(sort);
	}

	@Override
	public Study read(Long id) {
		return studyRepository.findById(id).get();
	}
	
	@Override
	public List<Study> findbyLeader(User user) {
		return studyRepository.findByLeader(user);
	}
	
	@Override
	public void insertLike(Likes like) {
		likeRepository.save(like);
		
	}

	@Override
	public Likes isLike(Long studyId, Long userId) {
		return likeRepository.checkLike(userId, studyId);
	}

	@Override
	public void deleteLike(Long id) {
		likeRepository.deleteById(id);
	}

	@Override
	public List<Likes> getLikes() {
		return likeRepository.findAll();
	}

	@Override
	public List<Board> findByStudyId(Long id) {
		Sort sort = sortByRegdate();
		Study study = studyRepository.findById(id).get();
		return boardRepository.findByStudy(study, sort);
	}

	@Override
	public void insertBoard(Board board) {
		boardRepository.save(board);
	}
	

	@Override
	public List<Study> findByStudynameLike(String word, Pageable pageable) {
//		word = "%"+word+"%";
		return studyRepository.findByTitleLike(word, pageable);
	}

//	@Override
//	public List<Study> findByLeaderLike(String word) {
//		word = "%"+word+"%";
//		return studyRepository.findByLeaderLike(word);
//	}

	@Override
	public Board findBoardById(Long id) {
		return boardRepository.findById(id).get();
	}

	@Override
	public void deleteBoard(Long id) {
		boardRepository.deleteById(id);
	}

	private Sort sortByRegdate() {
	    return Sort.by(Sort.Direction.DESC, "regdate");
	}

	@Override
	public void insertReply(Reply reply) {
		replyRepository.save(reply);
	}

	@Override
	public void deleteReply(Long id) {
		replyRepository.deleteById(id);
	}

	@Override
	public List<Reply> findReplyByBoard(Long id) {
		return replyRepository.findByBoard(boardRepository.findById(id).get());
	}

	@Override
	public int replyCountbyBoard(Board board) {
		return replyRepository.countByBoard(board);
	}

//	@Override
//	public long count() {
//		return studyRepository.count();
//	}

	@Override
	public List<Study> paging(Pageable pageable) {
		// TODO Auto-generated method stub
		return studyRepository.findAll(pageable).getContent();
	}

	@Override
	public List<Study> getList(Pageable pageable) {
		// TODO Auto-generated method stub
		return studyRepository.findAll(pageable).getContent();
	}
	@Override
	public long countStudy() {
		return studyRepository.count();
	}

	@Override
	public List<Study> getTop3Study() {
		return studyRepository.findTop3ByOrderByLikesDesc();
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		studyRepository.deleteById(id);
	}
}
