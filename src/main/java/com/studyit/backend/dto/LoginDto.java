package com.studyit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	title="로그인 DTO",
	description="로그인 시 필요한 이메일과 비밀번호 관련 정보가 들어있는 DTO 객체"
)
public class LoginDto {
	@Schema(title="이메일")
	private String email;
	
	@Schema(title="비밀번호")
	private String password;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
