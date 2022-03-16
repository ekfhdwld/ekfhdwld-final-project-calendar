package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.core.domain.entity.Engagement;

public interface EmailService {
	void sendEngagement(Engagement engagement);
}
