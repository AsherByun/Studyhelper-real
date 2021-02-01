package com.studyhelper;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.studyhelper.test.HelloController;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HelloController.class)
@AutoConfigureMockMvc
class StudyHelperEc21ApplicationTests {

	@Test
	void contextLoads() {
	}

}
