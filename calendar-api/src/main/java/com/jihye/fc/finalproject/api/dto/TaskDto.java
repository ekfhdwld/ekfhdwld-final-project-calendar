package com.jihye.fc.finalproject.api.dto;

import com.jihye.fc.finalproject.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class TaskDto implements ScheduleDto {
	
	private Long scheduleId;
	private LocalDateTime taskAt;
	private String title;
	private String description;
	private Long writerId;
	
	@Override
	public ScheduleType getScheduleType() {
		return ScheduleType.TASK;
	}
}
