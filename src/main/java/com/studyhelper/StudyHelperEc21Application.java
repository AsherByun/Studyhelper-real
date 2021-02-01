package com.studyhelper;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class StudyHelperEc21Application {

	public static String APPLICATION_LOCATIONS = "spring.config.location=classpath:application.yml";
	public static String LOCAL_LOCATION = "C:/Users/Owner/eclipse-workspace/config/real-application.yml";
	public static String DEPLOY_LOCATION = "/home/ubuntu/app/travis/config/real-application.yml";

	public static void main(String[] args) {
		setProperties();
		
		new SpringApplicationBuilder(StudyHelperEc21Application.class).properties(APPLICATION_LOCATIONS).run(args);
	}

	public static void setProperties() {
		File local = new File(LOCAL_LOCATION);
		File deploy = new File(DEPLOY_LOCATION);

		if (local.exists()) {
			APPLICATION_LOCATIONS += ", file:/" + LOCAL_LOCATION;
		}
		if (deploy.exists()) {
			APPLICATION_LOCATIONS += ", " + DEPLOY_LOCATION;
		}
	}
}
