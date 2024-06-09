package com.studyit.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.studyit.backend.model.Member;

public interface MemberRepository extends CrudRepository<Member, Integer>{
	@Query("select m from Member m where m.email = :email and m.password = :password and m.delYn = FALSE and m.emailAuthYn = TRUE")
	public List<Member> findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	@Query("select count(m.seq) from Member m where m.email = :email and m.delYn = FALSE and m.emailAuthYn = TRUE")
	public int countByEmail(@Param("email") String email);
	
	@Query("select count(m.seq) from Member m where m.nickname = :nickname and m.delYn = FALSE and m.emailAuthYn = TRUE")
	public int countByNickname(@Param("nickname") String nickname);
}
