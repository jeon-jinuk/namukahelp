package com.routine.domain.a_member.repository;

import com.routine.domain.a_member.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
}
