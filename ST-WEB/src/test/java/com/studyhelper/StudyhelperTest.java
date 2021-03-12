package com.studyhelper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = { "spring.config.location=C:/Users/Owner/eclipse-workspace/config/application-real.yml" })
public class StudyhelperTest {

	@Test
	public void mainTest() {
		log.info("테스트시작!!!");
		Assertions.assertEquals("aa", "aa");
	}
}
