package com.feignclient.quation.serviceimpl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.feignclient.quation.appconstants.AppConstants;
import com.feignclient.quation.entity.Quations;
import com.feignclient.quation.globalexceptionhandle.IdNotFoundException;
import com.feignclient.quation.repository.QuationRepository;
import com.feignclient.quation.service.QuationService;

@Service
public class QuationServiceImpl implements QuationService {

	private static final Logger logger = LogManager.getLogger(QuationServiceImpl.class);

	@Autowired
	QuationRepository quationRepository;

	@Override
	public List<Quations> getAllQuations() {
		logger.info("Fetching All Quations");
		List<Quations> all = quationRepository.findAll();
		logger.info("Number Of Quations Found :{}", all.size());
		return all;
	}

	@Override
	public Quations getQuationById(Long quationId) throws IdNotFoundException {
		logger.info("Fetch Quation With By ID: {}", quationId);
        Quations quation = quationRepository.findById(quationId).orElseThrow(() -> {
			logger.error("Quation with ID {} not found.", quationId);
			return new IdNotFoundException(AppConstants.ID_NOT_FOUND + quationId);
		});

		logger.info("Successfully fetched Quation: {}", quation);
		return quation;
	}

	@Override
	public Quations saveNewQuation(Quations quations) {
		logger.info("Save New Quation :{}", quations);
		Quations save = quationRepository.save(quations);
		logger.info(" Success Fully Quation Save :{}", save.getQuationId());
		return save;
	}

	@Override
	public List<Quations> getQutionsByQuizId(Long quizId) {
		logger.info("Fetch Request Send Quation By Quiz Id :{}", quizId);
		List<Quations> byQuizId = quationRepository.findByQuizId(quizId);
		logger.info("Success Fully Fetch Qution By Quiz Id :{}", byQuizId);
		return byQuizId;
	}

}
