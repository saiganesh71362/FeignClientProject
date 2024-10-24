package com.feignclient.quiz.service;

import java.util.List;

import com.feignclient.quiz.entity.Quiz;

public interface QuizService {

	public List<Quiz> getAllQuizTitle();

	public Quiz getQuizById(Long quizId) throws Exception;

	public Quiz saveQuizTitle(Quiz quiz);

	public Boolean deleteQuizTitle(Long quizId) throws Exception;

}
