package com.jihye.fc.finalproject.api.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class EventCreateReq {
	@NotBlank
	private String title;
	private String description;
	@NotNull
	private LocalDateTime startAt;
	@NotNull
	private LocalDateTime endAt;
	private List<Long> attendeeIds;
	
}
