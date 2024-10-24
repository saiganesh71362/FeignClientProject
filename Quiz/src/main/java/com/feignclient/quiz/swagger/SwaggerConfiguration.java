package com.feignclient.quiz.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

	@Bean
	public GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder()
				.group("Quiz")
				.packagesToScan("com.feignclient.quiz.controller") // Specify the package																						// to scan
				.build();
	}

}
