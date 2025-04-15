package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.one.springpj.model.Joiner;
import com.one.springpj.model.Study;
import com.one.springpj.model.User;

public interface JoinerRepository extends JpaRepository<Joiner, Long>{
//	@Query(value="select * from joiner where user_id=?1 and join_status=?2",nativeQuery=true)
	@Query(value="select j from Joiner j join fetch j.study where user_id=?1 and join_status=?2")
	public List<Joiner> findJoinUserList(Long userId, String joinStatus);
	
	@Query(value="select j from Joiner j join fetch j.study where leader_id=?1 and join_status='WAITING'")
	public List<Joiner> findApplyUser(Long leaderId);
	
	@Query(value="select count(*) from Joiner j where user_id=?1 and join_status=?2 and study_id=?3")
	public int joinCheck(Long id, String joinStatus, Long studyId);
	
	public Joiner findByStudyAndUser(Study study, User user);
	
	@Query(value="select count(*) from Joiner j where study_id=?1 and join_status=?2")
	public int joinCount(Long studyId, String joinStatus);
	
	@Query(value="select j from Joiner j where study_id=?1 and study_role='MEMBER'")
	public List<Joiner> findMemberByStudyId(Long id);
}
