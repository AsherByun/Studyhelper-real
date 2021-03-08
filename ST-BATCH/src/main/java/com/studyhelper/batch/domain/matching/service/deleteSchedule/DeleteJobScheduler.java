package com.studyhelper.batch.domain.matching.service.deleteSchedule;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.studyhelper.batch.domain.matching.entity.Matching;
import com.studyhelper.batch.domain.matching.repo.MatchingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteJobScheduler {
	private final MatchingRepository matchingRepository;
	//로그사용하기 위해서 expire 사용안함
	@Scheduled(fixedDelay = 86400000) // 하루에 한번 실행
	public void runDeleteJob() {
		log.info("매칭 삭제 시작!!");

		List<Matching> matchings = matchingRepository.findAll();

		for (Matching matching : matchings) {
			LocalDate localDate = LocalDate.parse(matching.getRequestMatchingDate(), DateTimeFormatter.ISO_DATE);
			LocalDate leastDay = LocalDate.now();
			leastDay = leastDay.minusDays(3);

			if (localDate.isBefore(leastDay)) {
				log.info("매칭 삭제 --> 매칭 정보: " + matching.getMemberId() + "의 매칭 삭제");
				matchingRepository.delete(matching);
			}
		}

	}
}
