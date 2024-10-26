package com.feignclient.quation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.feignclient.quation.appconstants.AppConstants;
import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.globalexceptionhandle.IdNotFoundException;
import com.feignclient.quation.repository.QuationRepository;
import com.feignclient.quation.serviceimpl.QuationServiceImpl;

@SpringBootTest
class QuationServiceImplTest {

	@Mock
	QuationRepository quationRepository;

	@InjectMocks
	QuationServiceImpl quationServiceImpl;

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

		when(quationRepository.findAll()).thenReturn(listOfQuations);

		List<Quations> allQuations = quationServiceImpl.getAllQuations();

		assertEquals(3, allQuations.size());

	}

	@Test
	@Order(2)
	void getQuationById() {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationRepository.findById(1l)).thenReturn(Optional.of(quation1));

		Quations quationById = quationServiceImpl.getQuationById(1l);

		assertEquals(quation1, quationById);
	}

	@Test
	@Order(3)
	void getQuationById_Failure() {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationRepository.findById(1l)).thenReturn(Optional.empty());

		IdNotFoundException assertThrows2 = assertThrows(IdNotFoundException.class,
				() -> quationServiceImpl.getQuationById(1l));

		assertEquals(AppConstants.ID_NOT_FOUND + 1, assertThrows2.getMessage());
	}

	@Test
	@Order(4)
	void saveNewQuation() {
		Quations quation1 = new Quations();
		quation1.setQuationId(1l);
		quation1.setQuationName("What is Java And Explain Features");
		quation1.setQuizId(1l);

		when(quationRepository.save(quation1)).thenReturn(quation1);

		Quations saveNewQuation = quationServiceImpl.saveNewQuation(quation1);
		assertEquals(quation1.getQuationId(), saveNewQuation.getQuationId());
		assertEquals(quation1.getQuationName(), saveNewQuation.getQuationName());
		assertEquals(quation1.getQuizId(), saveNewQuation.getQuizId());

		verify(quationRepository, timeout(1)).save(quation1);

	}

	@Test
	@Order(5)
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

		Quations quation3 = new Quations();
		quation3.setQuationId(3l);
		quation3.setQuationName("You Know Java 8 Features , Tell Me Some Java 8 Features");
		quation3.setQuizId(1l);
		listOfQuations.add(quation1);
		listOfQuations.add(quation2);
		listOfQuations.add(quation3);
		when(quationRepository.findByQuizId(1l)).thenReturn(listOfQuations);
		List<Quations> qutionsByQuizId = quationServiceImpl.getQutionsByQuizId(1l);
		assertEquals(3, qutionsByQuizId.size());
	}
}
