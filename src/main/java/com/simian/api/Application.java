package com.simian.api;

import com.simian.api.entities.AnalysisData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@ComponentScan
public class Application {

	private List<AnalysisData> analyzes = new ArrayList<>();

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
