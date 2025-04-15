package com.one.springpj.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.one.springpj.constant.Role;
import com.one.springpj.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUsername(String username);

	public List<User> findByRole(Role role, Sort sort);

	public List<User> findByUsernameLike(String word, Sort sort);

	public List<User> findByAddrLike(String word, Sort sort);

	public List<User> findByEmailLike(String word, Sort sort);

	public List<User> findByNickLike(String word, Sort sort);
}
