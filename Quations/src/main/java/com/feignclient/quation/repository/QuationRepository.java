package com.feignclient.quation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignclient.quation.entity.Quations;

public interface QuationRepository extends JpaRepository<Quations, Long> {
	
	List<Quations> findByQuizId(Long quizId);

}
