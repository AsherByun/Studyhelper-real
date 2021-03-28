package com.studyhelper.configuration;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	private String version;
	private String title;
	
	@Bean
	public Docket apiV1() {
		version = "V1";
		title = "StudyHelper API "+version;
		
		return new Docket(DocumentationType.SWAGGER_2)
				.useDefaultResponseMessages(false)
				.groupName(version)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.studyhelper.domain"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.apiInfo(apiInfo(title, version));
	}
	
	private ApiInfo apiInfo(String title,String version) {
		return new ApiInfo(
				title,
				"Swagger로 생성한 API DOCS",
				version,
				"www.studyhelper.com",
				new Contact("Contact Me", "www.studyhelper.com", "dfdo333@naver.com"),
				"Licenses",
				"www.studyhelper.com",
				new ArrayList<>()
				);
	}
}
