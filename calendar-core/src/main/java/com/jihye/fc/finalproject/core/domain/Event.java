package com.jihye.fc.finalproject.core.domain;

import com.jihye.fc.finalproject.core.domain.entity.Schedule;
import com.jihye.fc.finalproject.core.util.Period;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class Event {
	
	private Schedule schedule;
	
	public Event(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public boolean isOverlapped(LocalDateTime startAt, LocalDateTime endAt) {
		return schedule.getStartAt().isBefore(endAt) && startAt.isBefore(schedule.getEndAt());
	}
	
	public String getTitle(){
		return this.getSchedule().getTitle();
	}
	
	public Period getPeriod() {
		return Period.of(this.schedule.getStartAt(), this.schedule.getEndAt());
	}
}
