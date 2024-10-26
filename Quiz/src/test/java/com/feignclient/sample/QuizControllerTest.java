package com.feignclient.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import com.feignclient.quiz.controller.QuizController;
import com.feignclient.quiz.entity.Quations;
import com.feignclient.quiz.entity.Quiz;
import com.feignclient.quiz.serviceimpl.QuizServiceImpl;

@ComponentScan("com.feignclient.quiz")
@AutoConfigureOrder
@ContextConfiguration
@SpringBootTest(classes = { QuizControllerTest.class })
class QuizControllerTest {

	@Mock
	QuizServiceImpl quizServiceImpl;

	@InjectMocks
	QuizController quizController;

	@Test
	@Order(1)
	void getAllQuizTitle() {

		List<Quiz> quizList = new ArrayList<>();

		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);
		quiz.setQuizTitle("Java-Quiz");

		List<Quations> listOfQuations = new ArrayList<Quations>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("Explain Java-8 Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Multitreadding Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		quizList.add(quiz);

		when(quizServiceImpl.getAllQuizTitle()).thenReturn(quizList);

		ResponseEntity<List<Quiz>> allQuizTitle = quizController.getAllQuizTitle();

		assertEquals(HttpStatus.OK, allQuizTitle.getStatusCode());
		assertEquals(1, allQuizTitle.getBody().size());
	}

	@Test
	@Order(2)
	void getQuizById() throws Exception {

		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);
		quiz.setQuizTitle("Java-Quiz");

		List<Quations> listOfQuations = new ArrayList<Quations>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("Explain Java-8 Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Multitreadding Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		when(quizServiceImpl.getQuizById(1l)).thenReturn(quiz);

		ResponseEntity<Quiz> quizById = quizController.getQuizById(1l);
		assertEquals(HttpStatus.OK, quizById.getStatusCode());
		assertEquals(quiz, quizById.getBody());

	}

	@Test
	@Order(3)
	void saveQuizTitle() {
		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);
		quiz.setQuizTitle("Java-Quiz");

		List<Quations> listOfQuations = new ArrayList<Quations>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("Explain Java-8 Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Multitreadding Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		when(quizServiceImpl.saveQuizTitle(quiz)).thenReturn(quiz);
		ResponseEntity<Quiz> saveQuizTitle = quizController.saveQuizTitle(quiz);

		assertEquals(HttpStatus.CREATED, saveQuizTitle.getStatusCode());

		assertEquals(quiz, saveQuizTitle.getBody());
	}

	void deleteQuizTitle() throws Exception {
		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);
		quiz.setQuizTitle("Java-Quiz");

		List<Quations> listOfQuations = new ArrayList<Quations>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("Explain Java-8 Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Multitreadding Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		when(quizServiceImpl.deleteQuizTitle(1l)).thenReturn(true);

		ResponseEntity<Boolean> deleteQuizTitle = quizController.deleteQuizTitle(1l);

		assertEquals(HttpStatus.OK, deleteQuizTitle.getStatusCode());
		assertEquals(true, deleteQuizTitle.getBody());
	}

}
