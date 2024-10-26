package com.feignclient.quiz.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.feignclient.quiz.appconstants.AppConstants;
import com.feignclient.quiz.entity.Quiz;
import com.feignclient.quiz.globalexceptionhandle.IdNotFoundException;
import com.feignclient.quiz.repository.QuizRepository;
import com.feignclient.quiz.service.QuationClient;
import com.feignclient.quiz.service.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	private static final Logger logger = LogManager.getLogger(QuizServiceImpl.class);

	private QuizRepository quizRepository;

	private QuationClient quationClient;

	public QuizServiceImpl(QuizRepository quizRepository, QuationClient quationClient) {
		super();
		this.quizRepository = quizRepository;
		this.quationClient = quationClient;
	}

	@Override
	public List<Quiz> getAllQuizTitle() {
		logger.info("Fetching All Quiz Titles With Quations");
		List<Quiz> all = quizRepository.findAll();
		logger.debug("Fetching All Quizes From Data Base :{}", all.size());
		List<Quiz> collect = all.stream().map(quiz -> {
			logger.debug("Request Received Quizes By Id :{}", quiz.getQuizId());
			quiz.setQuations(quationClient.getQutionsByQuizId(quiz.getQuizId()));
			logger.debug("Sucess Fully Fetch Quizes By Id:{}", quiz.getQuizId());
			return quiz;
		}).collect(Collectors.toList());

		return collect;
	}

	@Override
	public Quiz getQuizById(Long quizId) throws IdNotFoundException {
		logger.info("Fetching Quiz By Id Along With Quations:{}", quizId);
		Quiz orElseThrow = quizRepository.findById(quizId).orElseThrow(() -> {
			logger.warn("Request Received Quiz Id Not Found :{}", quizId);
			return new IdNotFoundException(AppConstants.ID_NOT_FOUND + quizId);
		});
		logger.debug("Fetching Quiz Id Along With Quations Found :{}", quizId);
		orElseThrow.setQuations(quationClient.getQutionsByQuizId(quizId));
		logger.info("Success Fully Fetched Quiz Id With Quations :{}", quizId);
		return orElseThrow;
	}

	@Override
	public Quiz saveQuizTitle(Quiz quiz) {
		logger.info("Request Send New Quiz Save");
		Quiz save = quizRepository.save(quiz);
		logger.info("Success Fully Create New Quiz:{}", save);
		return save;
	}

	@Override
	public Boolean deleteQuizTitle(Long quizId) throws IdNotFoundException {
		logger.info("Request Received To Delete Qize By Id :{}", quizId);
		Optional<Quiz> byId = quizRepository.findById(quizId);
		if (byId.isPresent()) {
			logger.info("Received Request Delete ById With Quiz Id Is Preset :{}", byId);
			quizRepository.deleteById(quizId);
			logger.info("Success Fully Delete Quiz With Id :{}", byId);
			return true;
		} else {
			logger.info("Quiz Id Not Found :{}", byId);
			throw new IdNotFoundException(AppConstants.ID_NOT_FOUND + byId);
		}
	}

}
