package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.model.Alert;

public interface AlertRepository extends JpaRepository<Alert, Long>{
	public List<Alert> findByUserId(Long id);
}
