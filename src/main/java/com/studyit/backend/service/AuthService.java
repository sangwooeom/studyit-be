package com.studyit.backend.service;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;

public interface AuthService {
	public TokenDto createToken(LoginDto loginDto, int memberSeq);
	
	public void saveToken(TokenDto tokenDto, int memberSeq);
}
