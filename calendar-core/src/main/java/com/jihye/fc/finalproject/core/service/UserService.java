package com.jihye.fc.finalproject.core.service;

import com.jihye.fc.finalproject.core.domain.entity.User;
import com.jihye.fc.finalproject.core.domain.entity.repository.UserRepository;
import com.jihye.fc.finalproject.core.dto.UserCreateReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	
	@Transactional
	public User create(UserCreateReq userCreateReq) {
		userRepository.findByEmail(userCreateReq.getEmail())
		  .ifPresent(u -> {
			  throw new RuntimeException("user already existed");
		  });
		
		return userRepository.save(new User(
		    userCreateReq.getName(),
		    userCreateReq.getEmail(),
		    userCreateReq.getPassword(),
		    userCreateReq.getBirthday()
		));
	}
	
	@Transactional
	public Optional<User> findPwMatchUser(String email, String password) {
		return userRepository.findByEmail(email)
		  .map(user -> user.getPassword().equals(password) ? user : null);
	}
}
