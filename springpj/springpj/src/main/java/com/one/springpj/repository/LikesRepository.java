package com.one.springpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.one.springpj.model.Likes;

public interface LikesRepository extends JpaRepository<Likes, Long> {
	@Query(value="select * from likes where user_id=?1 and study_id=?2",nativeQuery = true)
	public Likes checkLike(Long userId, Long studyId);
}
