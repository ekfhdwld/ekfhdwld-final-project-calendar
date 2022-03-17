package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.api.dto.*;
import com.jihye.fc.finalproject.api.service.EventService;
import com.jihye.fc.finalproject.api.service.NotificationService;
import com.jihye.fc.finalproject.api.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
	
	private final TaskService taskService;
	private final EventService eventService;
	private final NotificationService notificationService;
	
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
	
	@PostMapping("/notifications")
	public ResponseEntity<Void> createNotifications(
		  @RequestBody NotificationCreateReq notificationCreateReq,
		  AuthUser authUser){
		notificationService.create(notificationCreateReq, authUser);
		return ResponseEntity.ok().build();
	}
	
}
