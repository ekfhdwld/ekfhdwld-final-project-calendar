package com.jihye.fc.finalproject.api.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SharedScheduleDto {
	private Long userId;
	private String name;
	private boolean me; //스케줄이 내 것인지
	private List<ScheduleDto> schedules;
}
