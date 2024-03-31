package com.bishal.factorydesign.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

	@Bean
	GroupedOpenApi controllerApi() {
		return GroupedOpenApi.builder().group("controller-api").packagesToScan("com.bishal.factorydesign")
				.build();
	}

}
