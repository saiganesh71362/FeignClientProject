package com.feignclient.sample;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feignclient.quiz.controller.QuizController;
import com.feignclient.quiz.entity.Quations;
import com.feignclient.quiz.entity.Quiz;
import com.feignclient.quiz.serviceimpl.QuizServiceImpl;

@AutoConfigureOrder
@ComponentScan("com.feignclient.quiz")
@SpringBootTest(classes = { QuizControllerMvcTest.class })
@ContextConfiguration
@AutoConfigureMockMvc
class QuizControllerMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	QuizServiceImpl quizServiceImpl;

	@InjectMocks
	QuizController quizController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(quizController).build();
	}

	@Test
	@Order(1)
	void getAllQuizTitle() throws Exception {

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

		this.mockMvc.perform(get("/quiz/getAllQuizTitles")).andExpect(status().isOk()).andDo(print());
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

		this.mockMvc.perform(get("/quiz/getQuizById/{quizId}", 1)).andDo(print());
	}

	@Test
	@Order(3)
	void saveQuizTitle() throws Exception {

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
		quation2.setQuationName("Explain Briefly Multithreading Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		when(quizServiceImpl.saveQuizTitle(quiz)).thenReturn(quiz);

		this.mockMvc
				.perform(post("/quiz/newQuizTitleAdd").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(quiz)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.quizId").value(1L))
				.andExpect(jsonPath("$.quizTitle").value("Java-Quiz"))
				.andExpect(jsonPath("$.quations[0].quationName").value("Explain Java-8 Features"))
				.andExpect(jsonPath("$.quations[1].quationName").value("Explain Briefly Multithreading Concept"))
				.andDo(print());

	}

	@Test
	@Order(4)
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
		quation2.setQuationName("Explain Briefly Multithreading Concept");
		quation2.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);

		quiz.setQuations(listOfQuations);

		when(quizServiceImpl.deleteQuizTitle(1L)).thenReturn(true);

		// Perform DELETE request and verify the response
		this.mockMvc.perform(delete("/quiz/deleteQuizTitle/{quizId}", 1L).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()) // Expect 200 OK if delete is successful
				.andDo(print());
	}
}
