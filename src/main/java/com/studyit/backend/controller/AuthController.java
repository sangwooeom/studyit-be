package com.studyit.backend.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.service.AuthService;
import com.studyit.backend.service.MemberService;
import com.studyit.backend.utils.AuthUtils;
import com.studyit.backend.utils.ResponseStatusExceptionUtils;

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
	
	@GetMapping("/v1/hash")
	public String hash(@RequestParam("email") String email, @RequestParam("password") String passowrd) {
		try {
			return AuthUtils.encrytPassword(email, passowrd);
		} catch (NoSuchAlgorithmException e) {
			throw ResponseStatusExceptionUtils.internalServerError();
		}
	}
}
