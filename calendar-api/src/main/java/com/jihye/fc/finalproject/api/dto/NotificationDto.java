package com.jihye.fc.finalproject.api.dto;

import com.jihye.fc.finalproject.core.domain.ScheduleType;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class NotificationDto implements ScheduleDto{
	
	private Long scheduleId;
	private LocalDateTime notifyAt;
	private String title;
	private Long writerId;
	
	@Override
	public ScheduleType getScheduleType() {
		return ScheduleType.NOTIFICATION;
	}
}
