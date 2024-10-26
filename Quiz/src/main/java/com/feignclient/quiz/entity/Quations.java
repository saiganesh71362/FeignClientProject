package com.feignclient.quiz.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quations {

	@JsonProperty("quationId")
	private Long quationId;
	@JsonProperty("quationName")
	private String quationName;
	@JsonProperty("quizId")
	private Long quizId;

}
