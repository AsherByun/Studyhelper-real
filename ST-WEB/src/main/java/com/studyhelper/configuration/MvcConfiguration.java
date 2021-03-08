package com.studyhelper.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.studyhelper.domain.member.interceptor.FirstAccessInterceptor;

@Configuration
public class MvcConfiguration implements WebMvcConfigurer {

//	@Override
//	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(new FirstAccessInterceptor()).excludePathPatterns("/signup", "/login", "/css/**",
//				"/js/**", "/testing/**");
//	}

}
