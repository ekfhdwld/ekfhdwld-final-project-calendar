package com.jihye.fc.finalproject.core.domain;

import com.jihye.fc.finalproject.core.domain.entity.Schedule;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class Event {
	
	private Schedule schedule;
	
	public Event(Schedule schedule) {
		this.schedule = schedule;
	}
}
