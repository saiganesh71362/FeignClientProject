package com.feignclient.quiz.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feignclient.quiz.entity.Quiz;
import com.feignclient.quiz.serviceimpl.QuizServiceImpl;

@RestController
@RequestMapping("/quiz")
public class QuizController {

	private static final Logger logger = LogManager.getLogger(QuizController.class);

	@Autowired
	QuizServiceImpl quizServiceImpl;

	@GetMapping("/getAllQuizTitles")
	public ResponseEntity<List<Quiz>> getAllQuizTitle() {
		logger.info("Request Received Retrived Get All Quizes");
		List<Quiz> allQuizTitle = quizServiceImpl.getAllQuizTitle();
		return new ResponseEntity<List<Quiz>>(allQuizTitle, HttpStatus.OK);
	}

	@GetMapping("/getQuizById/{quizId}")
	public ResponseEntity<Quiz> getQuizById(@PathVariable Long quizId) throws Exception {
		logger.info("Request Received Get Quiz By Id :{}", quizId);
		Quiz quizById = quizServiceImpl.getQuizById(quizId);
		logger.info("Success Fully Get Quiz By Id :{}", quizById);
		return new ResponseEntity<Quiz>(quizById, HttpStatus.OK);
	}

	@PostMapping("/newQuizTitleAdd")
	public ResponseEntity<Quiz> saveQuizTitle(@RequestBody Quiz quiz) {
		logger.info("Request Received Save New Quiz:{}", quiz);
		Quiz saveQuizTitle = quizServiceImpl.saveQuizTitle(quiz);
		logger.info("Sucess Fully Created New Quation:{}", saveQuizTitle);
		return new ResponseEntity<Quiz>(saveQuizTitle, HttpStatus.CREATED);

	}

	@DeleteMapping("/deleteQuizTitle/{quizId}")
	public ResponseEntity<Boolean> deleteQuizTitle(@PathVariable Long quizId) throws Exception {
		logger.info("Request Received Quiz Deleted By Id :{}", quizId);
		Boolean deleteQuizTitle = quizServiceImpl.deleteQuizTitle(quizId);
		logger.info("Success Fully Delete Quiz By Id :{}", quizId);
		return new ResponseEntity<Boolean>(deleteQuizTitle, HttpStatus.OK);
	}

}
