package com.studyit.backend.service;

import org.springframework.stereotype.Service;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.utils.AuthUtils;

@Service
public class AuthServiceImpl implements AuthService {
	@Override
	public TokenDto createToken(LoginDto loginDto, int memberSeq) {
		String email = loginDto.getEmail();
		
		String accessToken = AuthUtils.createAccessToken(email, memberSeq);
		String refreshToken = AuthUtils.createRefreshToken(email, memberSeq);
		
		TokenDto tokenDto = new TokenDto();
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
}
