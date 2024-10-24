package com.feignclient.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.feignclient.quiz.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
