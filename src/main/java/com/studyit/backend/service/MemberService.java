package com.studyit.backend.service;

import com.studyit.backend.dto.JoinDto;
import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.model.Member;

public interface MemberService {
	public Member getMemberByLoginDto(LoginDto loginDto);
	
	public boolean isDuplicateEmail(String email);
	
	public boolean isDuplicateNickname(String nickname);
	
	public void addMember(JoinDto joinDto);
}
