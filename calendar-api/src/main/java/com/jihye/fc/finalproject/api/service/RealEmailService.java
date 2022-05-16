package com.jihye.fc.finalproject.api.service;

import com.jihye.fc.finalproject.api.controller.BatchController;
import com.jihye.fc.finalproject.api.dto.EngagementEmailStuff;
import com.jihye.fc.finalproject.core.domain.entity.Share;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.internet.MimeMessage;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class RealEmailService implements EmailService{
	
	private final JavaMailSender emailSender;
	private final SpringTemplateEngine templateEngine;
	
	@Override
	public void sendEngagement(EngagementEmailStuff stuff) {
		final MimeMessagePreparator preparator = (MimeMessage message) -> {
			final MimeMessageHelper helper = new MimeMessageHelper(message);
			helper.setTo(stuff.getToEmail());
			helper.setSubject(stuff.getSubject());
			helper.setText(
			    templateEngine.process("engagement-email",
			            new Context(Locale.KOREAN, stuff.getProps())), true
			);
		};
		emailSender.send(preparator);
	}
	
	@Override
	public void sendAlarmMail(BatchController.SendMailBatchReq r) {
		System.out.println(r.toString());
	}
	
	@Override
	public void sendShareRequestMail(String email, String name, Share.Direction direction) {
		System.out.println("send share request mail. " + email + ", " + name + ", " + direction);
	}
}
