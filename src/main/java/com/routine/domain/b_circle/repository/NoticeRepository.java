package com.routine.domain.b_circle.repository;

import com.routine.domain.b_circle.model.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    List<Notice> findByCircleId(Long circleId);
}
