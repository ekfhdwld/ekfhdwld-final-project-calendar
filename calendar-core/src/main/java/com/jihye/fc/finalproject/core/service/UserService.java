package com.jihye.fc.finalproject.core.service;

import com.jihye.fc.finalproject.core.domain.entity.User;
import com.jihye.fc.finalproject.core.domain.entity.repository.UserRepository;
import com.jihye.fc.finalproject.core.dto.UserCreateReq;
import com.jihye.fc.finalproject.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final Encryptor bcryptEncryptor;
	private final UserRepository userRepository;
	
	@Transactional
	public User create(UserCreateReq req) {
		userRepository.findByEmail(req.getEmail())
		  .ifPresent(u -> {
			  throw new RuntimeException("user already existed");
		  });
		
		return userRepository.save(User.builder()
		  .name(req.getName())
		  .email(req.getEmail())
		  .password(bcryptEncryptor.encrypt(req.getPassword()))
		  .birthday(req.getBirthday())
		  .build());
	}
	
	@Transactional
	public Optional<User> findPwMatchUser(String email, String password) {
		return userRepository.findByEmail(email)
		  .map(user -> user.isMatch(bcryptEncryptor, password) ? user : null);
	}
}
