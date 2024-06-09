package com.studyit.backend.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="tb_member")
@Getter
@Setter
@Schema(
	title="회원 Model",
	description="tb_member 테이블에 조회하여 정보를 저장하는 Model 객체"
)
public class Member extends Common {
	public Member() {
		super();
		this.emailAuthYn = false;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int seq;
	
	@Column(name = "email")
	@Schema(title = "이메일")
	private String email;
	
	@Column(name = "password")
	@Schema(title = "비밀번호")
	private String password;
	
	@Column(name = "nickname")
	@Schema(title = "닉네임")
	private String nickname;
	
	@Column(name = "email_auth_yn")
	@Schema(title = "이메일 인증 여부")
	private boolean emailAuthYn;
}
