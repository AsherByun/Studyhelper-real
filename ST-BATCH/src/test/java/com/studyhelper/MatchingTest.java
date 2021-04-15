//package com.studyhelper;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.scheduling.annotation.EnableAsync;
//import org.springframework.test.context.ContextConfiguration;
//
//import com.studyhelper.domain.matching.entity.Matching;
//import com.studyhelper.domain.matching.entity.enums.Region;
//import com.studyhelper.domain.matching.entity.enums.Subject;
//import com.studyhelper.domain.matching.repo.MatchingRepository;
//import com.studyhelper.domain.matching.service.schedule.match.algorithm.Matcher;
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@SpringBootTest(properties = { "spring.config.location=C:/Users/Owner/eclipse-workspace/config/application-batch.yml" })
//@Transactional
//public class MatchingTest {
//	@Autowired
//	private Matcher matchTrie;
//
//	ArrayList<String> seqs = new ArrayList<String>();
//
//	@BeforeEach
//	@Transactional
//	void setup() {
////		for (int i = 0; i < 4; i++) {
////			Matching matching = new Matching();
////			matching.setMemberId("king" + i % 4);
////			matching.setRegion(Region.SEOUL_GANGNAM);
////			matching.setSize(4);
////			matching.setSubject(Subject.PROGRAMMING_C);
////			matching.setRequestMatchingDate("2021-03-13");
////			matchingRepository.save(matching);
////			seqs.add(matching.getSeq());
////		}
//	}
//
//	@AfterEach
//	@Transactional
//	void reset() {
////		for (String seq : seqs) {
////			if (matchingRepository.existsById(seq)) {
////				matchingRepository.deleteById(seq);
////			}
////		}
//	}
//
//	@Test
//	@Transactional
//	void 매칭확인() {
////		List<Matching> matchings = matchingRepository.findAll();
////		for (Matching m : matchings) {
////			matchTrie.pushMatching(m);
////		}
//	}
//}
