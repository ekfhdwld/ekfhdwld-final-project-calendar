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
@Table(name = "shares")
@Entity
public class Share extends BaseEntity{
	
	private Long fromUserId;
	private Long toUserId;
	
	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;
	
	@Enumerated(value = EnumType.STRING)
	private Direction direction;
	
	public Share reply(RequestReplyType type){
		switch (type){
			case ACCEPT:
				this.requestStatus = RequestStatus.ACCEPTED;
				break;
			case REJECT:
				this.requestStatus = RequestStatus.REJECTED;
				break;
		}
		return this;
	}
	
	public enum Direction{
		BI_DIRECTION, UNI_DIRECTION
	}
}
