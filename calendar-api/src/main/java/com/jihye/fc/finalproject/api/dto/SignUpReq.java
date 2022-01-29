package com.jihye.fc.finalproject.api.dto;

import lombok.*;

import java.time.LocalDate;

@Data
public class SignUpReq {
	private String name;
	private String email;
	private String password;
	private LocalDate birthday;
}
