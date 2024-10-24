package com.feignclient.quiz.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.feignclient.quiz.entity.Quations;

@FeignClient(url = "http://localhost:7112",value ="Quations-Client")
public interface QuationClient {
    @GetMapping("/quation/getQuationByQuizId/{quizId}")
    List<Quations> getQutionsByQuizId(@PathVariable("quizId") Long quizId);
}

