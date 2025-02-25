package com.example.dccbatch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.batch.core.launch.JobLauncher;

@SpringBootApplication
//@EnableBatchProcessing
public class Application {

	@Autowired
	private JobLauncher jobLauncher;


	@Autowired
	private Job myJob;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner runJobOnStartup() {
		return args -> {
			JobParameters jobParameters = new JobParametersBuilder()
					.addLong("timestamp", System.currentTimeMillis())
					.toJobParameters();
			jobLauncher.run(myJob, jobParameters);
		};
	}

}
