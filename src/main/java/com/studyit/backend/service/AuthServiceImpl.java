package com.studyit.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.model.Member;
import com.studyit.backend.model.Token;
import com.studyit.backend.repository.TokenRepository;
import com.studyit.backend.type.TokenType;
import com.studyit.backend.utils.AuthUtils;

@Service
public class AuthServiceImpl implements AuthService {
	@Autowired
	private TokenRepository tokenRepository;
	
	@Override
	public TokenDto createToken(LoginDto loginDto, Member member) {
		int memberSeq = member.getSeq();
		String email = loginDto.getEmail();
		
		String accessToken = AuthUtils.createAccessToken(email, memberSeq);
		String refreshToken = AuthUtils.createRefreshToken(email, memberSeq);
		
		TokenDto tokenDto = new TokenDto();
		tokenDto.setAccessToken(accessToken);
		tokenDto.setRefreshToken(refreshToken);
		
		return tokenDto;
	}
	
	@Override
	public void saveToken(TokenDto tokenDto, Member member) {
		String accessToken = tokenDto.getAccessToken();
		String refreshToken = tokenDto.getRefreshToken();
		
		saveToken(TokenType.ACCESS, accessToken, member);
		saveToken(TokenType.REFRESH, refreshToken, member);
	}
	
	private void saveToken(TokenType tokenType, String tokenValue, Member member) {
		Token token = new Token(tokenType, tokenValue, member);		
		tokenRepository.save(token);
	}
}
