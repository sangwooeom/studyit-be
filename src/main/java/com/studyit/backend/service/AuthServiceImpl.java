package com.studyit.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.model.Token;
import com.studyit.backend.repository.TokenRepository;
import com.studyit.backend.type.TokenType;
import com.studyit.backend.utils.AuthUtils;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private TokenRepository tokenRepository;
	
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
	
	@Override
	public void saveToken(TokenDto tokenDto, int memberSeq) {
		String accessToken = tokenDto.getAccessToken();
		String refreshToken = tokenDto.getRefreshToken();
		
		saveToken(TokenType.ACCESS, accessToken, memberSeq);
		saveToken(TokenType.REFRESH, refreshToken, memberSeq);
	}
	
	private void saveToken(TokenType tokenType, String tokenValue, int memberSeq) {
		Token token = new Token();
		
		token.setToken(tokenValue);
		token.setTokenType(tokenType);
		token.setMemberSeq(memberSeq);
		
		tokenRepository.save(token);
	}
}
