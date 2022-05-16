package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.dto.AuthUser;
import com.jihye.fc.finalproject.api.dto.CreateShareReq;
import com.jihye.fc.finalproject.core.domain.RequestStatus;
import com.jihye.fc.finalproject.core.domain.entity.RequestReplyType;
import com.jihye.fc.finalproject.core.domain.entity.Share;
import com.jihye.fc.finalproject.core.domain.entity.User;
import com.jihye.fc.finalproject.core.domain.entity.repository.ShareRepository;
import com.jihye.fc.finalproject.core.exception.CalendarException;
import com.jihye.fc.finalproject.core.exception.ErrorCode;
import com.jihye.fc.finalproject.core.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ShareService {
	
	private final UserService userService;
	private final ShareRepository shareRepository;
	private final EmailService emailService;
	
	@Transactional
	public void createShare(AuthUser authUser, CreateShareReq req) {
		final User fromUser = userService.findByUserId(authUser.getId());
		final User toUser = userService.findByUserId(req.getToUserId());
		shareRepository.save(Share.builder()
			.fromUserId(fromUser.getId())
			.toUserId(toUser.getId())
			.direction(req.getDirection())
			.requestStatus(RequestStatus.REQUESTED)
		    .build());
		emailService.sendShareRequestMail(toUser.getEmail(), fromUser.getName(), req.getDirection());
	}
	
	@Transactional
	public void replyToShareRequest(Long shareId, AuthUser toAuthUser, RequestReplyType type) {
		shareRepository.findById(shareId)
				  .filter(share -> share.getToUserId().equals(toAuthUser.getId()))
				  .filter(share -> share.getRequestStatus() == RequestStatus.REQUESTED)
				  .map(share -> share.reply(type))
				  .orElseThrow(() -> new CalendarException(ErrorCode.BAD_REQUEST));
	}
}
