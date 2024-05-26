package com.studyit.backend.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ResponseStatusExceptionUtils {
	private ResponseStatusExceptionUtils() {}
	
	public static ResponseStatusException internalServerError() {
		return new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다.");
	}
	
	public static ResponseStatusException unauthorized() {
		return new ResponseStatusException(HttpStatus.UNAUTHORIZED, "로그인이 실패하였습니다.");
	}
}
