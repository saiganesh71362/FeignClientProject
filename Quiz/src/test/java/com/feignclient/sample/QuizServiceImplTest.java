package com.feignclient.sample;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import com.feignclient.quiz.appconstants.AppConstants;
import com.feignclient.quiz.entity.Quations;
import com.feignclient.quiz.entity.Quiz;
import com.feignclient.quiz.globalexceptionhandle.IdNotFoundException;
import com.feignclient.quiz.repository.QuizRepository;
import com.feignclient.quiz.service.QuationClient;
import com.feignclient.quiz.serviceimpl.QuizServiceImpl;

@ContextConfiguration
@ComponentScan("com.feignclient.quiz")
@SpringBootTest(classes = { QuizServiceImplTest.class })
class QuizServiceImplTest {

	@Mock
	QuationClient quationClient;
	@Mock
	QuizRepository quizRepository;

	@InjectMocks
	QuizServiceImpl quizServiceImpl;

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

		when(quizRepository.findAll()).thenReturn(quizList);

		when(quationClient.getQutionsByQuizId(1L)).thenReturn(listOfQuations);

		List<Quiz> allQuizTitle = quizServiceImpl.getAllQuizTitle();

		assertEquals(1, allQuizTitle.size());

	}

	@Test
	@Order(2)
	void getQuizById() {

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

		when(quizRepository.findById(1l)).thenReturn(Optional.of(quiz));

		when(quationClient.getQutionsByQuizId(1l)).thenReturn(listOfQuations);

		Quiz quizById = quizServiceImpl.getQuizById(1l);

		assertEquals(quiz, quizById);
	}

	@Test
	@Order(3)
	void getQuizById_Failure() {

		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);

		when(quizRepository.findById(1l)).thenReturn(Optional.empty());
		IdNotFoundException assertThrows2 = assertThrows(IdNotFoundException.class,
				() -> quizServiceImpl.getQuizById(1l));

		assertEquals(AppConstants.ID_NOT_FOUND + 1, assertThrows2.getMessage());
	}

	@Test
	@Order(4)
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

		when(quizRepository.save(quiz)).thenReturn(quiz);
		Quiz saveQuizTitle = quizServiceImpl.saveQuizTitle(quiz);
		assertEquals(quiz, saveQuizTitle);
	}

	@Test
	@Order(5)
	void deleteQuizTitle() {

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

		when(quizRepository.findById(1l)).thenReturn(Optional.of(quiz));
//		when(quizRepository.deleteById(1l)).thenReturn(quiz);
//		when(quizRepository.deleteById(1l)).thenReturn(true);

		Boolean deleteQuizTitle = quizServiceImpl.deleteQuizTitle(1l);

		assertEquals(true, deleteQuizTitle);

	}

	@Test
	@Order(6)
	void deleteQuizTitle_Failure() {

		Quiz quiz = new Quiz();
		quiz.setQuizId(1l);

		when(quizRepository.findById(1l)).thenReturn(Optional.empty());

		IdNotFoundException assertThrows2 = assertThrows(IdNotFoundException.class,
				() -> quizServiceImpl.deleteQuizTitle(1l));
         assertEquals(AppConstants.ID_NOT_FOUND + "Optional.empty", assertThrows2.getMessage());
	}
}
