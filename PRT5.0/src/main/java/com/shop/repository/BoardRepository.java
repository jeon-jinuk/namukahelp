package com.shop.repository;


import com.shop.entity.BoardEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<BoardEntity, Long> {
    // update board_table set board_hits=board_hits+1 where id=?
    @Modifying
    @Query(value = "update BoardEntity b set b.hit=b.hit+1 where b.id=:id")
    void updateHits(@Param("id") Long id);

    Page<BoardEntity> findByTitleContaining(String searchKeyword, Pageable pageable);


}


















