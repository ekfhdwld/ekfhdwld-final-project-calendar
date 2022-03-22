package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.domain.entity.RequestReplyType;
import com.jihye.fc.finalproject.core.domain.entity.repository.EngagementRepository;
import com.jihye.fc.finalproject.core.exception.CalendarException;
import com.jihye.fc.finalproject.core.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EngagementService {
	private final EngagementRepository engagementRepository;
	
	@Transactional
	public RequestStatus update(AuthUser authUser, Long engagementId, RequestReplyType type) {
		return engagementRepository.findById(engagementId)
				  .filter(e -> e.getRequestStatus() == RequestStatus.REQUESTED)
				  .filter(e -> e.getAttendee().getId().equals(authUser.getId()))
				  .map(e -> e.reply(type))
				  .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST))
				  .getRequestStatus();
	}
}
