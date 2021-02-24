package com.studyhelper.batch.matching;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteJobScheduler {
	private final JobLauncher jobLauncher;
	private final DeleteMatchingJobConfig deleteMatchingJobConfig;
	
	@Scheduled(fixedDelay = 100000)
	public void runJob() {
		Map<String, JobParameter> confMap = new HashMap<String, JobParameter>();
		confMap.put("time", new JobParameter(System.currentTimeMillis()));
		JobParameters jobParameters = new JobParameters(confMap);
		
		try {
			log.info("=======================> 3일보다 더 오래된 매칭 요청 삭제~~~~~~~");
			log.info("오늘 날짜" + LocalDate.now().toString());
			jobLauncher.run(deleteMatchingJobConfig.deleteMatchingJob(), jobParameters);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
}
