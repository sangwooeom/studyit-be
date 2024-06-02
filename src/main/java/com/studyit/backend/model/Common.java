package com.studyit.backend.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public abstract class Common {
	public Common() {
		this.createDt = Instant.now();
		this.updateDt = Instant.now();
		this.delYn = false;
	}
	
	@Column(name = "create_dt")
	private Instant createDt;
	
	@Column(name = "update_dt")
	private Instant updateDt;
	
	@Column(name = "del_yn")
	private boolean delYn;
}
