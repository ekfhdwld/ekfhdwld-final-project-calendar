package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.api.dto.TaskCreateReq;
import com.jihye.fc.finalproject.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
	
	private final static String LOGIN_SESSION_KEY = "USER_ID";
	private final TaskService taskService;
	
	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(
	        @RequestBody TaskCreateReq taskCreateReq,
	        HttpSession session){
		final Long userId = (Long) session.getAttribute("LOGIN_SESSION_KEY");
		if(userId == null){
			throw new RuntimeException("bad request. no session");
		}
		taskService.create(taskCreateReq, AuthUser.of(userId));
		return ResponseEntity.ok().build();
	}
	
}
