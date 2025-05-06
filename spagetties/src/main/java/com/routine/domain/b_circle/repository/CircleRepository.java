package com.routine.domain.b_circle.repository;

import com.routine.domain.b_circle.model.Circle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CircleRepository extends JpaRepository<Circle, Long> {

    boolean existsByName(String name);

    List<Circle> findByCreatorId(Long creatorId);

    List<Circle> findByIsPublicAndTagsContaining(boolean isPublic, String keyword);

    List<Circle> findByIsPublic(boolean isPublic);

    List<Circle> findByTagsContaining(String keyword);
}
