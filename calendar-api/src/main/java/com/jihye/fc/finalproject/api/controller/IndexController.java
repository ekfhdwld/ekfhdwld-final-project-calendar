package com.jihye.fc.finalproject.api.controller;

import com.jihye.fc.finalproject.core.domain.entity.RequestReplyType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {
	
	private final static String LOGIN_SESSION_KEY = "USER_ID";
	
	@GetMapping("/")
	public String index(Model model, HttpSession session, @RequestParam(required = false) String redirect){
		model.addAttribute("isSignIn", session.getAttribute(LOGIN_SESSION_KEY) != null);
		model.addAttribute("redirect", redirect);
		return "index";
	}
	
	@GetMapping("/events/engagements/{engagementId}")
		public String updateEngagement(
		    @PathVariable Long engagementId,
		    RequestReplyType type,
			Model model,
			HttpSession httpSession){
		model.addAttribute("isSignIn", httpSession.getAttribute(LOGIN_SESSION_KEY));
		model.addAttribute("updateType", type.name());
		model.addAttribute("engagementId", engagementId);
		model.addAttribute("path", "/events/engagements/" + engagementId + "?type=" + type.name());
		return "update-event";
	}
}
