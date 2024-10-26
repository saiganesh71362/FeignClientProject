package com.feignclient.quation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.feignclient.quation.controller.QuationController;
import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.serviceimpl.QuationServiceImpl;

@SpringBootTest
class QuationControllerTest {

	@Mock
	QuationServiceImpl quationServiceImpl;

	@InjectMocks
	QuationController quationController;

	@Test
	@Order(1)
	void getAllQuations() {
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

		ResponseEntity<List<Quations>> allQuations = quationController.getAllQuations();

		assertEquals(HttpStatus.OK, allQuations.getStatusCode());
		assertEquals(3, allQuations.getBody().size());

	}

	@Test
	@Order(2)
	void getQuationById() throws Exception {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationServiceImpl.getQuationById(1l)).thenReturn(quation1);

		ResponseEntity<Quations> quationById = quationController.getQuationById(1l);

		assertEquals(HttpStatus.OK, quationById.getStatusCode());
		assertEquals(quation1, quationById.getBody());

	}

	@Test
	@Order(3)
	void saveNewQuation() {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationServiceImpl.saveNewQuation(quation1)).thenReturn(quation1);

		ResponseEntity<Quations> saveNewQuation = quationController.saveNewQuation(quation1);
		assertEquals(HttpStatus.CREATED, saveNewQuation.getStatusCode());
		assertEquals(quation1, saveNewQuation.getBody());

	}

	@Test
	@Order(4)
	void getQutionsByQuizId() {
		
		ArrayList<Quations> listOfQuations = new ArrayList<>();

		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		Quations quation2 = new Quations();
		quation2.setQuationId(2l);
		quation2.setQuationName("Explain Breafly Exception In Java How Many Types In Java Exceptions");
		quation2.setQuizId(1l);

		listOfQuations.add(quation1);
		listOfQuations.add(quation2);
		when(quationServiceImpl.getQutionsByQuizId(1l)).thenReturn(listOfQuations);

		ResponseEntity<List<Quations>> qutionsByQuizId = quationController.getQutionsByQuizId(1l);

		assertEquals(HttpStatus.OK, qutionsByQuizId.getStatusCode());
		assertEquals(2, qutionsByQuizId.getBody().size());
	}
}
