package com.jihye.fc.finalproject.api.util;

import com.jihye.fc.finalproject.api.dto.EventDto;
import com.jihye.fc.finalproject.api.dto.NotificationDto;
import com.jihye.fc.finalproject.api.dto.ScheduleDto;
import com.jihye.fc.finalproject.api.dto.TaskDto;
import com.jihye.fc.finalproject.core.domain.entity.Schedule;

/**
 * abstract 로 만들면 생성자가 아예 호출이 안됨
 * 전부 하위에 스태틱 메소드만 두고싶다 하면 이렇게도 선언 자주 함
 */
public abstract class DtoConverter {
	
	public static ScheduleDto fromSchedule(Schedule schedule){
		switch (schedule.getScheduleType()){
			case EVENT :
				return EventDto.builder()
				  .scheduleId(schedule.getId())
				  .description(schedule.getDescription())
				  .startAt(schedule.getStartAt())
				  .endAt(schedule.getEndAt())
				  .title(schedule.getTitle())
				  .writerId(schedule.getWriter().getId())
				  .build();
			case TASK :
				return TaskDto.builder()
				  .scheduleId(schedule.getId())
				  .description(schedule.getDescription())
				  .taskAt(schedule.getStartAt())
				  .title(schedule.getTitle())
				  .writerId(schedule.getWriter().getId())
				  .build();
			case NOTIFICATION:
				return NotificationDto.builder()
				  .scheduleId(schedule.getId())
				  .writerId(schedule.getWriter().getId())
				  .notifyAt(schedule.getStartAt())
				  .title(schedule.getTitle())
				  .build();
			default :
				throw new RuntimeException("bad request. not matched schedule type.");
		}
	}
}
