package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.api.dto.EventCreateReq;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.domain.entity.Engagement;
import com.jihye.fc.finalproject.core.domain.entity.Schedule;
import com.jihye.fc.finalproject.core.domain.entity.User;
import com.jihye.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.jihye.fc.finalproject.core.domain.entity.repository.ScheduleRepository;
import com.jihye.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
	
	private final EmailService emailService;
	private final UserService userService;
	private final ScheduleRepository scheduleRepository;
	private final EngagementRepository engagementRepository;
	
	public void create(EventCreateReq eventCreateReq, AuthUser authUser) {
		/**
		 * 이벤트 참여자의 다른 이벤트와 중복 금지
		 * 1-2까지 회의가 있는데 추가로 다른 회의를 등록할 수 없음
		 * 추가로 이메일 발송....?
		 */
		
		//실제 실무에서는 findAll 이렇게 사용안함 : 데이터가 엄청 많으니까...!
		final List<Engagement> engagementList = engagementRepository.findAll();
		//engagementList에 하나라도 겹치는 게 있는지 확인
		if(engagementList.stream()
		      .anyMatch(e -> eventCreateReq.getAttendeeIds().contains(e.getAttendee().getId())
	                    && e.getRequestStatus() == RequestStatus.ACCEPTED
		                && e.getEvent().isOverlapped(
							eventCreateReq.getStartAt(),
			                eventCreateReq.getEndAt()))
		){
			throw new RuntimeException("cannot make engagement. period overlapped!");
		}
		
		final Schedule eventSchedule = Schedule.event(
			  eventCreateReq.getTitle(),
			  eventCreateReq.getDescription(),
			  eventCreateReq.getStartAt(),
			  eventCreateReq.getEndAt(),
			  userService.findByUserId(authUser.getId())
		);
		
		scheduleRepository.save(eventSchedule);
		eventCreateReq.getAttendeeIds()
			  .forEach(atId -> {
				  final User attendee = userService.findByUserId(atId);
				  final Engagement engagement = Engagement.builder()
					    .schedule(eventSchedule)
					    .requestStatus(RequestStatus.REQUESTED)
					    .attendee(attendee)
					    .build();
				  engagementRepository.save(engagement);
				  emailService.sendEngagement(engagement);
			  });
		System.out.println("engagement 저장");
		
		
	}
}
