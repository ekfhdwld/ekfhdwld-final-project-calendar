package com.jihye.fc.finalproject.api.config;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.core.exception.CalendarException;
import com.jihye.fc.finalproject.core.exception.ErrorCode;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.jihye.fc.finalproject.api.service.LoginService.LOGIN_SESSION_KEY;


public class AuthUserResolver implements HandlerMethodArgumentResolver {
	
	@Override
	public boolean supportsParameter(MethodParameter parameter){
		//해당 객체의 클래스 타입이 AuthUser의 클래스인지 확인
		return AuthUser.class.isAssignableFrom(parameter.getParameterType());
	}
	
	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		final Long userId = (Long) webRequest.getAttribute(LOGIN_SESSION_KEY, webRequest.SCOPE_SESSION);
		if(userId == null){
			throw new CalendarException(ErrorCode.BAD_REQUEST);
		}
		return AuthUser.of(userId);
	}
	
	
	
}
