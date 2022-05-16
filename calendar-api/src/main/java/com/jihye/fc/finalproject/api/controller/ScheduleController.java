package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.api.dto.*;
import com.jihye.fc.finalproject.api.service.*;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
	
	private final ScheduleQueryService scheduleQueryService; //조회담당 서비스
	private final TaskService taskService;
	private final EventService eventService;
	private final EngagementService engagementService;
	private final NotificationService notificationService;
	private final ShareService shareService;
	
	@PostMapping("/tasks")
	public ResponseEntity<Void> createTask(
	        @RequestBody TaskCreateReq taskCreateReq,
	        AuthUser authUser){
		taskService.create(taskCreateReq, authUser);
		return ResponseEntity.ok().build();
	}
	
	@PostMapping("/events")
	public ResponseEntity<Void> createEvent(
			@Valid @RequestBody EventCreateReq eventCreateReq,
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
	
	@GetMapping("/day")
	public List<ScheduleDto> getScheduleByDay(
	    AuthUser authUser,
	    @RequestParam(required = false)
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		return scheduleQueryService.getScheduleByDay(authUser, date == null ? LocalDate.now() : date);
	}
	
	@GetMapping("/week")
	public List<ScheduleDto> getScheduleByWeek(
	  AuthUser authUser,
	  @RequestParam(required = false)
	  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek){
		return scheduleQueryService.getScheduleByWeek(authUser, startOfWeek == null ? LocalDate.now() : startOfWeek);
	}
	
	@GetMapping("/month")
	public List<ScheduleDto> getScheduleByMonth(
	  AuthUser authUser,
	  @RequestParam(required = false)
	  @DateTimeFormat(pattern = "yyyy-MM") String yearMonth){ //2022-03
		return scheduleQueryService.getScheduleByMonth(authUser, yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth));
	}
	
	@PutMapping("/events/engagements/{engagementId}")
	public RequestStatus updateEngagement(
		  @Valid @RequestBody ReplyReq replyEngagementReq,
		  @PathVariable Long engagementId,
		  AuthUser authUser
	){
		return engagementService.update(authUser, engagementId, replyEngagementReq.getType());
	}
	
	@PostMapping("/shares")
	public void shareSchedule(
	    AuthUser authUser,
	    @Valid @RequestBody CreateShareReq req
	){
		shareService.createShare(authUser, req);
	}
	
	@PutMapping("/shares/{shareId}")
	public void replyToShareRequest(
	    @PathVariable Long shareId,
	    @Valid @RequestBody ReplyReq replyReq,
	    AuthUser authUser
	){
		shareService.replyToShareRequest(shareId, authUser, replyReq.getType());
	}
}
