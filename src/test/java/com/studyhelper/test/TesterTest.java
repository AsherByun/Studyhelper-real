package com.studyhelper.test;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TesterTest {
	@Autowired
	TestReopository testRepo;

	@Test
	public void TestTesting() {
		Tester tester = new Tester();
		tester.setName("변성재");
		testRepo.save(tester);

		Optional<Tester> getTester = testRepo.findById(1L);
		if (getTester.isPresent()) {
			System.out.println(getTester.get().getName());
		}
	}
}
