package com.jihye.fc.finalproject.batch.job;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SendMailBatchReq {
	private Long id;  //schedule id
	private LocalDateTime startAt;
	private String title;
	private String userMail;
}
