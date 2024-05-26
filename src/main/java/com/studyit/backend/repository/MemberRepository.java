package com.studyit.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.studyit.backend.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer>{
	public List<Member> findByEmailAndPassword(String email, String password);
}
