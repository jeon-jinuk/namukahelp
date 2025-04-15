package com.one.springpj.service;

import java.security.Principal;
import java.util.List;

import com.one.springpj.constant.Role;
import com.one.springpj.model.Alert;
import com.one.springpj.model.User;

public interface UserService {
	
	public void register(User user);

	public User findByUsername(String username);
	
	public User findById(Long id);
	
	public List<User> getUserlist();
	public void delete(long id);
	
	public void update(User user);
	
	public long userCount();
	
	void userdelete(User user, Principal principal);

	void userupdate(User user, Principal principal);
	
	public List<User> findByRole(Role role);
	
	public List<User> findByUsernameLike(String word);
	public List<User> findByNickLike(String word);
	public List<User> findByAddrLike(String word);
	public List<User> findByEmailLike(String word);
	
	public List<Alert> findAlertByUserId(Long userId);
	public Alert findAlertById(Long id);
	public void saveAlert(Alert alert);
	public void deleteAlert(Long id);
}
