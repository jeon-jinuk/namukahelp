package com.one.springpj.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.one.springpj.model.CafeMenu;
import com.one.springpj.repository.BranchRepository;
import com.one.springpj.repository.CafeMenuRepository;

@Service
public class CafeMenuServiceImpl implements CafeMenuService {
	
	@Autowired
	private BranchRepository branchRepository;
	
	@Autowired
	private CafeMenuRepository cafeMenuRepository;

	@Override
	public void save(CafeMenu cafeMenu) {
		cafeMenuRepository.save(cafeMenu);
		
	}



}
