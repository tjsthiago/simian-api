package com.simian.api;

import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.ports.repository.IRepository;
import com.simian.api.useCase.ports.repository.InMemoryRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

	private List<AnalysisData> analyzes = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public IRepository getRepository(){
		return new InMemoryRepository(analyzes);
	}

}
