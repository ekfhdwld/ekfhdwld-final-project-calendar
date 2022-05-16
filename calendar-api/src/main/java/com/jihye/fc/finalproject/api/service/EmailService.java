package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.controller.BatchController;
import com.jihye.fc.finalproject.api.dto.EngagementEmailStuff;
import com.jihye.fc.finalproject.core.domain.entity.Share;

public interface EmailService {
	void sendEngagement(EngagementEmailStuff stuff);
	void sendAlarmMail(BatchController.SendMailBatchReq r);
	void sendShareRequestMail(String email, String name, Share.Direction direction);
}
