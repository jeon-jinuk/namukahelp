package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.one.springpj.model.Study;
import com.one.springpj.model.User;

public interface StudyRepository extends JpaRepository<Study, Long>{
	@Query("select s from Study s where s.title like %?1%")
	public List<Study> findByTitleLike(String word, Pageable pageable);
	
	public List<Study> findByLeaderLike(String word);

	public List<Study> findByLeader(User user);
	
	public List<Study> findTop3ByOrderByLikesDesc();

}
