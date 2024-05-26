package com.studyit.backend.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.xml.bind.DatatypeConverter;

public class AuthUtils {
	private final static String SECRET_KEY_STR = "studyit";
	
	private final static long ACCESS_TOKEN_DURATION = 30L * 60L * 1000L;
	
	private final static long REFRESH_TOKEN_DURATION = 24L * 60L * 60L * 1000L;
	
	private final static SecretKey SECRETE_KEY;
	
	static {
		SECRETE_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET_KEY_STR));
	}
	
	private AuthUtils() {};
	
	public static String encrytPassword(String email, String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("SHA-512");
		
		md.update(email.getBytes());
		md.update(SECRET_KEY_STR.getBytes());
		md.update(password.getBytes());
		
		return DatatypeConverter.printBase64Binary(md.digest());
	}
	
	public static String createAccessToken(String email, int memberSeq) {
		return createToken(memberSeq, email, ACCESS_TOKEN_DURATION);
	}
	
	public static String createRefreshToken(String email, int memberSeq) {
		return createToken(memberSeq, email, REFRESH_TOKEN_DURATION);
	}
	
	private static Date createIssuedDate() {
		return new Date();
	}
	
	private static Date createExpiration(Date issuedDate, long duration) {
		return new Date(issuedDate.getTime() + duration);
	}
	
	private static String createToken(int seq, String issuer, long duration) {
		Date issuedDate = createIssuedDate();
		Date expirationDate = createExpiration(issuedDate, duration);
		
		return Jwts.builder()
				.claim("seq", seq)
				.issuer(issuer)
				.issuedAt(issuedDate)
				.expiration(expirationDate)
				.signWith(SECRETE_KEY, Jwts.SIG.HS512)
				.compact();
	}
}
