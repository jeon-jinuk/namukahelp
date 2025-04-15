package com.one.springpj.service;

import java.util.List;

import com.one.springpj.model.Branch;
import com.one.springpj.model.User;
import com.one.springpj.model.CafeMenu;
import com.one.springpj.model.Seat;

public interface BranchService {
	
	public void insert(Branch branch);

	public List<Branch> branchList();

	public void delete(Long id);

	public Branch findById(Long id);
	

	public void update(Branch branch);
	

	public Branch findByManager(User user);

	public Long countIdMax();
	public Long count();
	
	public void insertSeat(Seat seat);
	public List<Seat> findSeatByBranch(Branch branch);
	public void deleteSeat(List<Seat> seatList);
	
	
	public List<Seat> findByBranchId(Long id);
	
	public List<CafeMenu> cafeMenufindByBranchId(Branch id); 
	public CafeMenu cafeMenuFindById(Long id);
}
