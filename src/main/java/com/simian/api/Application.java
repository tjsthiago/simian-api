package com.simian.api;

import com.simian.api.entities.AnalysisData;
import com.simian.api.useCase.dnaSequenceAnalyzes.ports.DnaSequenceAnalysisRepository;
import com.simian.api.useCase.dnaSequenceAnalyzes.repository.InMemoryDnaSequenceAnalysisRepository;
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
	public DnaSequenceAnalysisRepository getRepository(){
		return new InMemoryDnaSequenceAnalysisRepository(analyzes);
	}

}
