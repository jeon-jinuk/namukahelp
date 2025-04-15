package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Board;
import com.one.springpj.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long>{
	public List<Reply> findByBoard(Board board);
	public int countByBoard(Board board);
}
