package com.studyit.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.studyit.backend.service.MemberService;

@RestController
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/api/v1/member/email/duplicate")
	public boolean isDuplicateEmail(@RequestParam("email") String email) {
		return memberService.isDuplicateEmail(email);
	}
}
