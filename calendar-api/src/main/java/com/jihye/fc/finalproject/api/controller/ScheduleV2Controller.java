package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.api.dto.ScheduleDto;
import com.jihye.fc.finalproject.api.dto.SharedScheduleDto;
import com.jihye.fc.finalproject.api.service.ScheduleQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v2/schedules")
public class ScheduleV2Controller {
	
	private final ScheduleQueryService scheduleQueryService; //조회담당 서비스
	
	@GetMapping("/day")
	public List<SharedScheduleDto> getScheduleByDay(
	    AuthUser authUser,
	    @RequestParam(required = false)
	    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date){
		return scheduleQueryService.getSharedScheduleByDay(authUser, date == null ? LocalDate.now() : date);
	}
	
	@GetMapping("/week")
	public List<SharedScheduleDto> getScheduleByWeek(
	  AuthUser authUser,
	  @RequestParam(required = false)
	  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startOfWeek){
		return scheduleQueryService.getSharedScheduleByWeek(authUser, startOfWeek == null ? LocalDate.now() : startOfWeek);
	}
	
	@GetMapping("/month")
	public List<SharedScheduleDto> getScheduleByMonth(
	  AuthUser authUser,
	  @RequestParam(required = false)
	  @DateTimeFormat(pattern = "yyyy-MM") String yearMonth){ //2022-03
		return scheduleQueryService.getSharedScheduleByMonth(authUser, yearMonth == null ? YearMonth.now() : YearMonth.parse(yearMonth));
	}
	

}
