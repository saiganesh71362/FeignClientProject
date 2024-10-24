package com.feignclient.quation.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
	
	@Bean
	public GroupedOpenApi controllerApi() {
		return GroupedOpenApi.
				builder()
				.group("Quations")
				.packagesToScan("com.feignclient.quation.controller") // Specify the // package// to scan
				.build();
	}

}
