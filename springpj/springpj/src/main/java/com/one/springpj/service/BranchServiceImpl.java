package com.one.springpj.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.one.springpj.model.Branch;
import com.one.springpj.model.User;
import com.one.springpj.model.CafeMenu;
import com.one.springpj.model.Seat;
import com.one.springpj.repository.BranchRepository;
import com.one.springpj.repository.CafeMenuRepository;
import com.one.springpj.repository.SeatRepository;

@Service
public class BranchServiceImpl implements BranchService{
	
	@Autowired
	private BranchRepository branchRepository;
	
	
	@Autowired
	private SeatRepository seatRepository;
	
	@Autowired
	private CafeMenuRepository cafeMenuRepository;

//	@Transactional
	@Override
	public void insert(Branch branch) {
		// TODO Auto-generated method stub
		branchRepository.save(branch);
		
	}
	@Override
	public List<Branch> branchList(){
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return branchRepository.findAll(sort);
	}
//	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		branchRepository.deleteById(id);
		
	}
	
	@Override
	public Branch findById(Long id) {
		Branch branch = branchRepository.findById(id).get();
		return branch;
	}
	
	@Override
	public void update(Branch branch) {
		Branch b = branchRepository.findById(branch.getId()).get();
		b.setProfile(branch.getProfile());
		b.setName(branch.getName());
		b.setAddr(branch.getAddr());
		b.setPhone(branch.getPhone());
		b.setManager(branch.getManager());
		branchRepository.save(b);
	}
	@Override
	public Branch findByManager(User user) {
		return branchRepository.findByManager(user);
	
	}
	@Override
	public void insertSeat(Seat seat) {
		seatRepository.save(seat);
		
	}
	@Override
	public Long countIdMax() {
		return branchRepository.countMax();
	}
	@Override
	public Long count() {
		return branchRepository.count();
	}
	@Override
	public List<Seat> findByBranchId(Long id) {
		return seatRepository.findByBranchId(id);
	}
	@Override
	public List<CafeMenu> cafeMenufindByBranchId(Branch branch) {
		return cafeMenuRepository.findByBranch(branch);
	}
	@Override
	public CafeMenu cafeMenuFindById(Long id) {
		return cafeMenuRepository.findById(id).get();
	}
	@Override
	public List<Seat> findSeatByBranch(Branch branch) {
		return seatRepository.findByBranch(branch);
	}
	@Override
	public void deleteSeat(List<Seat> seatList) {
		for(Seat seat : seatList) {
			seatRepository.deleteById(seat.getId());
		}
		
	}
	

}
