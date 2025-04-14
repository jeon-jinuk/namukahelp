package com.shop.entity;


import com.shop.dto.BoardDTO;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

// DB의 테이블 역할을 하는 클래스
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class BoardEntity extends BaseEntity1 {
    @Id // pk 컬럼 지정. 필수
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    @Column(length = 20, nullable = false) // 크기 20, not null
    private String boardWriter;

    @Column
    private String title;

    @Column(length = 500)
    private String content;

    @Column
    private int hit;

    @Column
    private int fileAttached; // 1 or 0

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<com.shop.entity.BoardFileEntity> boardFileEntityList = new ArrayList<>();

    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.REMOVE, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<com.shop.entity.CommentEntity> commentEntityList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    public static BoardEntity toSaveEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setHit(0);
        boardEntity.setFileAttached(0); // 파일 없음.
        return boardEntity;
    }

    public static BoardEntity toUpdateEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(boardDTO.getId());
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setHit(boardDTO.getHit());
        return boardEntity;
    }

    public static BoardEntity toSaveFileEntity(BoardDTO boardDTO) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setBoardWriter(boardDTO.getBoardWriter());
        boardEntity.setTitle(boardDTO.getTitle());
        boardEntity.setContent(boardDTO.getContent());
        boardEntity.setHit(0);
        boardEntity.setFileAttached(1); // 파일 있음.
        return boardEntity;
    }
}











