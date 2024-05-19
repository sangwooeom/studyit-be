package com.studyit.backend.service;

import java.security.NoSuchAlgorithmException;

import com.studyit.backend.dto.LoginDto;

public interface AuthService {
	public String hashPassword(LoginDto loginDto) throws NoSuchAlgorithmException;
}
