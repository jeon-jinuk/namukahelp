package com.one.springpj.service;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.one.springpj.constant.Role;
import com.one.springpj.model.Alert;
import com.one.springpj.model.User;
import com.one.springpj.repository.AlertRepository;
import com.one.springpj.repository.UserRepository;

import lombok.extern.java.Log;

@Service
@Log
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private AlertRepository alertRepository;

	@Override
	public void register(User user) {
		userRepository.save(user);
	}

	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public List<User> getUserlist() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Sort.Direction.DESC, "id");
		return userRepository.findAll(sort);
	}

	@Override
	public void delete(long id) {
		userRepository.deleteById(id);

	}

	@Override
	public void update(User user) {
		User u = userRepository.findByUsername(user.getUsername());// findbyid처럼 jpa에서 제공되는 함수가아닌 정의한함수는 .get() 할필요X
		log.info("========================" + u.getRole());
		log.info("========================" + u.getUsername());
		log.info("========================" + u.getEmail());
		u.setProfile(user.getProfile());

		u.setNick(user.getNick());
		u.setAddr(user.getAddr());
		u.setEmail(user.getEmail());
		userRepository.save(u);
	}

	@Override
	public void userdelete(User user, Principal principal) {
		// TODO Auto-generated method stub
		if (user == principal) {
			userRepository.delete(user);
		}

	}

	@Override
	public void userupdate(User user, Principal principal) {
		// TODO Auto-generated method stub
		if (user == principal) {
			userRepository.save(user);
		}
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

	@Override
	public List<User> findByRole(Role role) {
		return userRepository.findByRole(role, sortByRegdate());
	}
	

	@Override
	public List<User> findByUsernameLike(String word) {
		word = "%"+word+"%";
		return userRepository.findByUsernameLike(word, sortByRegdate());
	}

	@Override
	public List<User> findByNickLike(String word) {
		word = "%"+word+"%";
		return userRepository.findByNickLike(word, sortByRegdate());
	}

	@Override
	public List<User> findByAddrLike(String word) {
		word = "%"+word+"%";
		return userRepository.findByAddrLike(word, sortByRegdate());
	}

	@Override
	public List<User> findByEmailLike(String word) {
		word = "%"+word+"%";
		return userRepository.findByEmailLike(word, sortByRegdate());
	}
	

	public Sort sortByRegdate() {
		return Sort.by(Sort.Direction.DESC, "regdate");
	}

	@Override
	public List<Alert> findAlertByUserId(Long userId) {
		return alertRepository.findByUserId(userId);
	}

	@Override
	public void saveAlert(Alert alert) {
		alertRepository.save(alert);
	}

	@Override
	public void deleteAlert(Long id) {
		alertRepository.deleteById(id);
	}

	@Override
	public Alert findAlertById(Long id) {
		return alertRepository.findById(id).get();
	}

	@Override
	public long userCount() {
		return userRepository.count();
	}
}
