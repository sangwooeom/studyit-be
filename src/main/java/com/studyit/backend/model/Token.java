package com.studyit.backend.model;

import com.studyit.backend.type.TokenType;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_token")
@Getter
@Setter
@Schema(
	title = "토큰 Model",
	description = "tb_token 테이블을 조회하여 정보를 저장하는 Model 객체"
)
public class Token extends Common {
	@Id
	@Column(name = "token")
	@Schema(title = "토큰")
	private String token;
	
	@Column(name = "token_type")
	@Schema(title = "토큰 유형")
	@Enumerated(EnumType.STRING)
	private TokenType tokenType;
	
	@Column(name = "member_seq")
	@Schema(title = "회원 고유 번호")
	private int memberSeq;
}
