package com.feignclient.quation.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quations {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long quationId;
	private String quationName;
	private Long quizId;

}
