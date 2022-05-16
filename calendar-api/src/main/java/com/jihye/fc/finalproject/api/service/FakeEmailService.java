package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.controller.BatchController;
import com.jihye.fc.finalproject.api.dto.EngagementEmailStuff;
import com.jihye.fc.finalproject.core.domain.entity.Engagement;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile("test")
@Service
public class FakeEmailService implements EmailService{
	
	@Override
	public void sendEngagement(EngagementEmailStuff stuff) {
		System.out.println("send email. email:" + stuff.getSubject());
	}
	
	@Override
	public void sendAlarmMail(BatchController.SendMailBatchReq r) {
		System.out.println("send email. email:" + r.toString());
	}
	
	
}
