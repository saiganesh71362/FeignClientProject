package com.feignclient.quation;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.feignclient.quation.controller.QuationController;
import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.serviceimpl.QuationServiceImpl;

@SpringBootTest(classes = { QuationControllerMockMvcTest.class })
@ContextConfiguration
@ComponentScan("com.feignclient.quation")
@AutoConfigureMockMvc
class QuationControllerMockMvcTest {

	@Autowired
	MockMvc mockMvc;

	@Mock
	QuationServiceImpl quationServiceImpl;
	@InjectMocks
	QuationController quationController;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(quationController).build();
	}

	@Test
	@Order(1)
	void getAllQuations() throws Exception {
		ArrayList<Quations> listOfQuations = new ArrayList<>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Exception In Java How Many Types In Java Exceptions");
		quation2.setQuizId(1l);

		Quations quation3 = new Quations();
		quation3.setQuationId(3l);
		quation3.setQuationName("You Know Java 8 Features , Tell Me Some Java 8 Features");
		quation3.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);
		listOfQuations.add(quation3);

		when(quationServiceImpl.getAllQuations()).thenReturn(listOfQuations);

		this.mockMvc.perform(get("/quation/getAllQuation")).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(2)
	void getQuationById() throws Exception {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationServiceImpl.getQuationById(1l)).thenReturn(quation1);

		this.mockMvc.perform(get("/quation/getQuationById/{quationId}", 1)).andExpect(status().isOk()).andDo(print());
	}

	@Test
	@Order(3)
	void saveNewQuation() throws Exception {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationServiceImpl.saveNewQuation(quation1)).thenReturn(quation1);

		this.mockMvc
				.perform(post("/quation/newQuationAdd").contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(quation1)))
				.andExpect(status().isCreated()).andExpect(jsonPath("$.quationId").value(1l))
				.andExpect(jsonPath("$.quationName").value("What is Java And Explain Features"))
				.andExpect(jsonPath("$.quizId").value(1l));
	}

	@Test
	@Order(4)
	void getQutionsByQuizId() throws Exception {

		ArrayList<Quations> listOfQuations = new ArrayList<>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Briefly Exception In Java How Many Types In Java Exceptions");
		quation2.setQuizId(1l);

		listOfQuations.add(quation1);
		listOfQuations.add(quation2);
		when(quationServiceImpl.getQutionsByQuizId(1l)).thenReturn(listOfQuations);

		this.mockMvc.perform(get("/quation/getQuationByQuizId/{quizId}", 1))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].quationId").value(1L))
				.andExpect(jsonPath("$[0].quationName").value("What is Java And Explain Features"))
				.andExpect(jsonPath("$[1].quationId").value(2L))
				.andExpect(jsonPath("$[1].quationName")
				.value("Explain Briefly Exception In Java How Many Types In Java Exceptions"));
	}
}
