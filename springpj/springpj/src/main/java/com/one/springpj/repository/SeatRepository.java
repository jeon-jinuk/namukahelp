package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.one.springpj.model.Branch;
import com.one.springpj.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long>{
	@Query(value="select * from seat where branch_id=?1",nativeQuery = true)
	public List<Seat> findByBranchId(Long id);
	
//	@Query("select s from Seat s join fetch s.book where bookdate between ?1 and ?2")
//	public List<Seat> findByBookDate(String startDate, String endDate);
	
	@Query("select s from Seat s where seat_id=?1")
	public List<Seat> findByBookDate(Long id);
	
	public Seat findByBranchAndName(Branch branch, String name);
	
	public List<Seat> findByBranch(Branch branch);
	
}
