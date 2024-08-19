package com.studyit.backend.controller;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.dto.TokenDto;
import com.studyit.backend.model.Member;
import com.studyit.backend.service.AuthService;
import com.studyit.backend.service.MemberService;
import com.studyit.backend.utils.AuthUtils;
import com.studyit.backend.utils.ResponseStatusExceptionUtils;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthService authService;
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("v1/api/login")
	public TokenDto login(@RequestBody LoginDto loginDto) {
		Member member = memberService.getMemberByLoginDto(loginDto);
		TokenDto tokenDto = authService.createToken(loginDto, member);
		authService.saveToken(tokenDto, member);
		return tokenDto;
	}
	
	@GetMapping("v1/hash")
	public String hash(@RequestParam("email") String email, @RequestParam("password") String passowrd) {
		try {
			return AuthUtils.encrytPassword(email, passowrd);
		} catch (NoSuchAlgorithmException e) {
			throw ResponseStatusExceptionUtils.internalServerError();
		}
	}
}
