package com.jihye.fc.finalproject.core.domain.entity;

import com.jihye.fc.finalproject.core.util.Encryptor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@NoArgsConstructor
@Table(name = "users")
@Entity
public class User extends BaseEntity{
	
	private String name;
	private String email;
	private String password;  //hashed
	private LocalDate birthday;
	
	@Builder
	public User(String name, String email, String password, LocalDate birthday) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.birthday = birthday;
	}
	
	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public LocalDate getBirthday() {
		return birthday;
	}
	
	public boolean isMatch(Encryptor encryptor, String pw) {
		return encryptor.isMatch(pw, this.password);
	}
}
