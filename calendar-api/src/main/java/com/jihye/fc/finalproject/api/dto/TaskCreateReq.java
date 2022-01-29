package com.jihye.fc.finalproject.api.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskCreateReq {
	private String title;
	private String description;
	private LocalDateTime taskAt;
}
