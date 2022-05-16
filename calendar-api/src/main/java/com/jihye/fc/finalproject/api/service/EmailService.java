package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.controller.BatchController;
import com.jihye.fc.finalproject.api.dto.EngagementEmailStuff;

public interface EmailService {
	void sendEngagement(EngagementEmailStuff stuff);
	void sendAlarmMail(BatchController.SendMailBatchReq r);
}
