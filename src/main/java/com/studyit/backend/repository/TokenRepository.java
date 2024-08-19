package com.studyit.backend.repository;

import org.springframework.data.repository.CrudRepository;

import com.studyit.backend.model.Token;

public interface TokenRepository extends CrudRepository<Token, String>{
	
}
