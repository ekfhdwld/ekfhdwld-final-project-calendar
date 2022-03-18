package com.jihye.fc.finalproject.core.domain.entity;

import com.jihye.fc.finalproject.core.domain.Event;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "engagements")
@Entity
public class Engagement extends BaseEntity{
	
	@JoinColumn(name ="schedule_id")
	@ManyToOne
	private Schedule schedule;
	
	@JoinColumn(name ="attendee_id")
	@ManyToOne
	private User attendee;
	
	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;
	
	public Event getEvent(){ return schedule.toEvent(); }
	
	public boolean isOverlapped(Period period){
		return this.schedule.isOverlapped(period);
	}
}
