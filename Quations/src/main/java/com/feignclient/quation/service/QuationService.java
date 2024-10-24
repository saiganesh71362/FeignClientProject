package com.feignclient.quation.service;

import java.util.List;

import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.globalexceptionhandle.IdNotFoundException;

public interface QuationService {

	List<Quations> getAllQuations();

	Quations getQuationById(Long quationId) throws IdNotFoundException;

	Quations saveNewQuation(Quations quations);

	List<Quations> getQutionsByQuizId(Long quizId);

}
