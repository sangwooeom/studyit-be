package com.studyit.backend.service;

import com.studyit.backend.dto.LoginDto;

public interface MemberService {
	public int getMemberSeq(LoginDto loginDto);
}
