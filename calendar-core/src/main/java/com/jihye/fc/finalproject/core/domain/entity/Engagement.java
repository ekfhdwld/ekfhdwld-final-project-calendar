package com.jihye.fc.finalproject.core.domain.entity;

import com.jihye.fc.finalproject.core.domain.RequestStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Table(name = "engagements")
@Entity
public class Engagement {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JoinColumn(name ="schedule_id")
	@ManyToOne
	private Schedule schedule;
	
	@JoinColumn(name ="attendee_id")
	@ManyToOne
	private User attendee;
	private LocalDateTime createdAt = LocalDateTime.now();
	private RequestStatus requestStatus;
	
}
