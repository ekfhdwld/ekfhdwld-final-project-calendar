package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.dto.LoginReq;
import com.jihye.fc.finalproject.api.dto.SignUpReq;
import com.jihye.fc.finalproject.core.domain.entity.User;
import com.jihye.fc.finalproject.core.dto.UserCreateReq;
import com.jihye.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {
	
	private final static String LOGIN_SESSION_KEY = "USER_ID";
	private final UserService userService;
	
	@Transactional
	public void signUp(SignUpReq signUpReq, HttpSession session){
	
		final User user = userService.create(new UserCreateReq(
		    signUpReq.getName(),
		    signUpReq.getEmail(),
		    signUpReq.getPassword(),
		    signUpReq.getBirthday()
		));
		session.setAttribute(LOGIN_SESSION_KEY, user.getId());
	}
	
	@Transactional
	public void login(LoginReq loginReq, HttpSession session){
		final Long userId = (Long) session.getAttribute(LOGIN_SESSION_KEY);
		if(userId != null) return;
		
		final Optional<User> user =
		    userService.findPwMatchUser(loginReq.getEmail(), loginReq.getPassword());
		if(user.isPresent()){
			session.setAttribute(LOGIN_SESSION_KEY, user.get().getId());
		}else{
			throw new RuntimeException("password or email not match");
		}
	}
	
	public void logout(HttpSession session){
		session.removeAttribute(LOGIN_SESSION_KEY);
	}
}
