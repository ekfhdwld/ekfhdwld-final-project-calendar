package com.jihye.fc.finalproject.core.domain.entity;

import com.jihye.fc.finalproject.core.domain.Event;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.exception.CalendarException;
import com.jihye.fc.finalproject.core.exception.ErrorCode;
import com.jihye.fc.finalproject.core.util.Period;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
	
	public Engagement reply(RequestReplyType type){
		System.out.println(type);
		switch (type){
			case ACCEPT:
				System.out.println(type.name());
				this.requestStatus = RequestStatus.ACCEPTED;
				break;
			case REJECT:
				System.out.println(type.name());
				this.requestStatus = RequestStatus.REJECTED;
				break;
			default:
				throw new CalendarException(ErrorCode.BAD_REQUEST);
		}
		return this;
	}
}
