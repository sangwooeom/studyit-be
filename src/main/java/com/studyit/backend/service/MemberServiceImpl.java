package com.studyit.backend.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.studyit.backend.dto.JoinDto;
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
	public Member getMemberByLoginDto(LoginDto loginDto) {
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
			
			Member member = members.get(0);
			
			return member;
			
		} catch (NoSuchAlgorithmException nsaExp) {
			nsaExp.printStackTrace();
			log.error("해당 서버에서 SHA-512 알고리즘을 사용할 수가 없습니다.");
			throw ResponseStatusExceptionUtils.internalServerError();
		}
	}
	
	@Override
	public boolean isDuplicateEmail(String email) {
		int cnt = memberRepository.countByEmail(email);
		
		if (cnt > 2) {
			log.error("tb_member 테이블 안에 삭제되지 않은 동일한 이메일이 2개 이상 있습니다.");
			throw ResponseStatusExceptionUtils.internalServerError();
		}
		
		return cnt == 1;
	}
	
	@Override
	public boolean isDuplicateNickname(String nickname) {
		int cnt = memberRepository.countByNickname(nickname);
		
		if (cnt > 2) {
			log.error("tb_member 테이블 안에 삭제되지 않은 동일한 닉네임이 2개 이상 있습니다.");
			throw ResponseStatusExceptionUtils.internalServerError();
		}
		
		return cnt == 1;
	}
	
	@Override
	public void addMember(JoinDto joinDto) {
		try {
			Member member = createMemberByJoinDto(joinDto);
			memberRepository.save(member);
		} catch (NoSuchAlgorithmException nsae) {
			log.error("해당 서버에서 SHA-512 알고리즘을 사용할 수가 없습니다.");
			throw ResponseStatusExceptionUtils.internalServerError();
		} catch (Exception e) {
			log.error(e.getMessage());
			throw ResponseStatusExceptionUtils.internalServerError();
		}
	}
	
	private Member createMemberByJoinDto(JoinDto joinDto) throws NoSuchAlgorithmException {
		String email = joinDto.getEmail();
		String password = joinDto.getPassword();
		String nickname = joinDto.getNickname();
		
		Member member = new Member();
		
		member.setEmail(email);
		member.setNickname(nickname);
		member.setPassword(AuthUtils.encrytPassword(email, password));
		
		return member;
	}
}
