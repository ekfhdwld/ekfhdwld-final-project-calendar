package com.jihye.fc.finalproject.api.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class SignUpReq {
	@NotBlank
	private String name;
	
	@Email
	@NotBlank
	private String email;
	
	@Size(min = 6, message = "6자리 이상 입력해 주세요")
	@NotBlank
	private String password;
	
	@NotNull
	private LocalDate birthday;
}
