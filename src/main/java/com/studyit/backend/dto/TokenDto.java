package com.studyit.backend.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
	title="토큰 DTO",
	description="인증 토큰 관련 정보가 들어있는 DTO 객체"
)
public class TokenDto {
	@Schema(
		title="액세스 토큰",
		description="인증 시 사용하는 토큰으로 최대 30분까지만 사용이 가능하다."
	)
	public String accessToken;
	
	@Schema(
		title="리프레쉬 토큰",
		description="액세트 토큰이 만료될 때 재발급 시 사용하는 토큰으로 최대 24시간까지만 사용이 가능한다."
	)
	public String refreshToken;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
