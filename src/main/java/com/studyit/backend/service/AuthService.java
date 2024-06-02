package com.studyit.backend.service;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.model.Member;

public interface AuthService {
	public TokenDto createToken(LoginDto loginDto, Member member);
	
	public void saveToken(TokenDto tokenDto, Member member);
}
