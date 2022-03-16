package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.api.dto.EventCreateReq;
import com.jihye.fc.finalproject.api.dto.TaskCreateReq;
import com.jihye.fc.finalproject.api.service.EventService;
import com.jihye.fc.finalproject.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
	
	private final TaskService taskService;
	private final EventService eventService;
	
	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(
	        @RequestBody TaskCreateReq taskCreateReq,
	        AuthUser authUser){
		taskService.create(taskCreateReq, authUser);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/events")
	public ResponseEntity<Void> createEvent(
			@RequestBody EventCreateReq eventCreateReq,
			AuthUser authUser){
		eventService.create(eventCreateReq, authUser);
		return ResponseEntity.ok().build();
	}
	
}
