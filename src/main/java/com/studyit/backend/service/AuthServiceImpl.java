package com.studyit.backend.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Service;

import com.studyit.backend.dto.LoginDto;

import jakarta.xml.bind.DatatypeConverter;

@Service
public class AuthServiceImpl implements AuthService {
	private static final String SECRET_KEY = "studyit";
	
	@Override
	public String hashPassword(LoginDto loginDto) throws NoSuchAlgorithmException {
		String email = loginDto.getEmail();
		String password = loginDto.getPassword();
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException nsaExp) {
			nsaExp.printStackTrace();
			
		}
		
		md.update(email.getBytes());
		md.update(SECRET_KEY.getBytes());
		md.update(password.getBytes());
		
		return DatatypeConverter.printBase64Binary(md.digest());
	}
}
