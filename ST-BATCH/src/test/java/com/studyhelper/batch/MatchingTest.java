package com.studyhelper.batch;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = {
		"spring.config.location=C:/Users/Owner/eclipse-workspace/config/application-real.yml" }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class MatchingTest {
	@Autowired
	private MatchingRepository matchingRepository;
	
	
	@BeforeEach
	@Transactional
	void setup() {
		for(int i=0;i<20;i++) {
			Matching matching = new Matching();
		}
	}
}
