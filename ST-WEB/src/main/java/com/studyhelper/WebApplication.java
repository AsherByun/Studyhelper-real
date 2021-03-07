package com.studyhelper;

import java.io.File;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@EntityScan(basePackages = {"com.studyhelper.domain"})
@SpringBootApplication
public class WebApplication {
	//yml 위치정보 지정
	public static String APPLICATION_LOCATIONS = "spring.config.location=";
	//배포환경의 yml 위치
	public static String LOCAL_LOCATION = "C:/Users/Owner/eclipse-workspace/config/application-real.yml";
	//local환경의 yml 위치
	public static String DEPLOY_LOCATION = "/home/ubuntu/app/travis/config/application-real.yml";

	public static void main(String[] args) {
		setProperties();
		new SpringApplicationBuilder(WebApplication.class).properties(APPLICATION_LOCATIONS).run(args);
	}

	public static void setProperties() {
		//local환경과 deploy환경의 운영체제가 다르기때문에 따로 path를 지정해주었음
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
