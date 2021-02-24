package com.studyhelper.batch;

import java.io.File;
import java.util.Arrays;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@EntityScan(basePackages = {"com.studyhelper.domain"})
@SpringBootApplication
public class BatchApplication {

	public static String APPLICATION_LOCATIONS = "spring.config.location=";
	public static String LOCAL_LOCATION = "C:/Users/Owner/eclipse-workspace/config/application-batch.yml";
	public static String DEPLOY_LOCATION = "/home/ubuntu/app/travis/config/application-batch.yml";

	public static void main(String[] args) {
		setProperties();
		new SpringApplicationBuilder(BatchApplication.class).properties(APPLICATION_LOCATIONS).run(args);
	}

	public static void setProperties() {
		File local = new File(LOCAL_LOCATION);
		File deploy = new File(DEPLOY_LOCATION);

		if (local.exists()) {
			APPLICATION_LOCATIONS += LOCAL_LOCATION;
		}
		if (deploy.exists()) {
			APPLICATION_LOCATIONS += DEPLOY_LOCATION;
		}
	}
}
