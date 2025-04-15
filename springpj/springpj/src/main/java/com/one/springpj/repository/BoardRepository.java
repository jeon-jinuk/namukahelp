package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Board;
import com.one.springpj.model.Study;

public interface BoardRepository extends JpaRepository<Board, Long>{
//	@Query("select r from Board r join fetch r.study where study_id=?1")
//	public List<Board> findBoardList(Long studyId);
	public List<Board> findByStudy(Study study, Sort sort);

}
