package com.feignclient.quation.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.serviceimpl.QuationServiceImpl;

@RestController
@RequestMapping("/quation")
public class QuationController {

	private static final Logger logger = LogManager.getLogger(QuationController.class);

	@Autowired
	QuationServiceImpl quationServiceImpl;

	@GetMapping("/getAllQuation")
	public ResponseEntity<List<Quations>> getAllQuations() {
		logger.info("Request Received To Get All Quations");
		List<Quations> allQuations = quationServiceImpl.getAllQuations();
		logger.info("Sucess Fully Retrived Quations :{}", allQuations.size());
		return new ResponseEntity<>(allQuations, HttpStatus.OK);
	}

	@GetMapping("/getQuationById/{quationId}")
	public ResponseEntity<Quations> getQuationById(@PathVariable Long quationId) throws Exception {
		logger.info("Request Received To Get Quation By Id :{}", quationId);
		Quations quationById = quationServiceImpl.getQuationById(quationId);
		logger.info("Success Fully Retrived Get Quation By Id ,{} ", quationById);
		return new ResponseEntity<Quations>(quationById, HttpStatus.OK);
	}

	@PostMapping("/newQuationAdd")
	public ResponseEntity<Quations> saveNewQuation(@RequestBody Quations quations) {
		logger.info("Request Received To New Qutaion Save :{}", quations);
		Quations saveNewQuation = quationServiceImpl.saveNewQuation(quations);
		logger.info("Success Fully Create New Quation:{}", saveNewQuation);
		return new ResponseEntity<Quations>(saveNewQuation, HttpStatus.CREATED);
	}

	@GetMapping("/getQuationByQuizId/{quizId}")
	public ResponseEntity<List<Quations>> getQutionsByQuizId(@PathVariable Long quizId) {
		logger.info("Request Received To Get Quation By Quiz Id,{}", quizId);
		List<Quations> qutionsByQuizId = quationServiceImpl.getQutionsByQuizId(quizId);
		logger.info("Success Fully Get Quations By Quiz Id :{}", qutionsByQuizId);
		return new ResponseEntity<>(qutionsByQuizId, HttpStatus.OK);
	}

}
