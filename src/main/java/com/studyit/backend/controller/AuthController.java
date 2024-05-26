package com.studyit.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.service.AuthService;
import com.studyit.backend.service.MemberService;

@RestController
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("/v1/login")
	public TokenDto login(@RequestBody LoginDto loginDto) {
		int memberSeq = memberService.getMemberSeq(loginDto);
		TokenDto tokenDto = authService.createToken(loginDto, memberSeq);
		return tokenDto;
	}
}
