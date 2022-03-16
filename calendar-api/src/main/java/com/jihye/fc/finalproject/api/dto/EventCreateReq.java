package com.jihye.fc.finalproject.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreateReq {
	private String title;
	private String description;
	private LocalDateTime startAt;
	private LocalDateTime endAt;
	private List<Long> attendeeIds;
	
}
