package com.jihye.fc.finalproject.api.dto;

import com.jihye.fc.finalproject.core.domain.entity.Share;
import lombok.Data;

@Data
public class CreateShareReq {
	
	private Long toUserId;
	private Share.Direction direction;
}
