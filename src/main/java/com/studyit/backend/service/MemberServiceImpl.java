package com.studyit.backend.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.studyit.backend.dto.LoginDto;
import com.studyit.backend.model.Member;
import com.studyit.backend.repository.MemberRepository;
import com.studyit.backend.utils.AuthUtils;
import com.studyit.backend.utils.ResponseStatusExceptionUtils;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	private MemberRepository memberRepository;
	
	@Override
	public int getMemberSeq(LoginDto loginDto) {
		try {
			String email = loginDto.getEmail();
			String password = loginDto.getPassword();
			
			String hashPassword = AuthUtils.encrytPassword(email, password);
			List<Member> members = memberRepository.findByEmailAndPassword(email, hashPassword);
			
			if (members.size() > 1) {
				log.error("tb_tokne 테이블 안에 중복된 아이디가 존재해서 오류가 발생하였습니다.");
				throw ResponseStatusExceptionUtils.internalServerError();
			} else if (members.size() == 0) {
				log.error("해당 이메일과 비밀번호와 일치하는 정보가 tb_member 테이블에 없습니다.");
				throw ResponseStatusExceptionUtils.unauthorized();
			}
			
			int memberSeq = members.get(0).getSeq();
			
			return memberSeq;
			
		} catch (NoSuchAlgorithmException nsaExp) {
			nsaExp.printStackTrace();
			log.error("해당 서버에서 SHA-512 알고리즘을 사용할 수가 없습니다.");
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "서버에 오류가 발생하였습니다.");
		}
	}
}
