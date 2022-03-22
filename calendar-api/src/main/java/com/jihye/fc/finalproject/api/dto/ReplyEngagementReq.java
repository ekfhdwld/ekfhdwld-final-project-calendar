package com.jihye.fc.finalproject.api.dto;

import com.jihye.fc.finalproject.core.domain.entity.RequestReplyType;

public class ReplyEngagementReq {
	
	private RequestReplyType type; //REJECT, ACCEPT
	
	public ReplyEngagementReq(){}
	
	public ReplyEngagementReq(RequestReplyType type){
		this.type = type;
	}
	
	public RequestReplyType getType(){
		return type;
	}
	
}
