package com.studyhelper.matching.job;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.entity.MemberTeam;
import com.studyhelper.matching.job.match.algorithm.MatchTrie;

import java.util.Iterator;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class DeleteMatchingJobConfig {
	public static final String JOB_NAME = "DeleteMatchingJob";
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;
	private final CreateDateJobParameter createDateJobParameter;
	private int chunkSize;
	private final MatchTrie matchTrie;

	@Value("${chunkSize:100}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}

	@Bean(JOB_NAME + "_createDateJopParameter")
	@JobScope
	public CreateDateJobParameter CreateDateJobParameter() {
		return new CreateDateJobParameter();
	}

	@Bean(name = JOB_NAME)
	public Job matchingJob() {
		matchTrie.setMatchs();
		return jobBuilderFactory.get(JOB_NAME).start(deleteMatching()).incrementer(new RunIdIncrementer()).build();
	}

	@Bean(name = JOB_NAME + "_deleteMatchingStep")
	@JobScope
	public Step deleteMatching() {
		return stepBuilderFactory.get(JOB_NAME + "_deleteMatchingStep").<Matching, Matching>chunk(chunkSize)
				.reader(deleteMatchingReader()).writer(deleteWriter()).build();
	}

	@Bean(name = JOB_NAME + "_deleteMatchingReader")
	@StepScope
	public JpaPagingItemReader<Matching> deleteMatchingReader() {
		return new JpaPagingItemReaderBuilder<Matching>().name(JOB_NAME + "_deleteMatchingReader")
				.entityManagerFactory(entityManagerFactory).pageSize(chunkSize).queryString("SELECT m FROM Matching m")
				.build();
	}

	public ItemWriter<Matching> deleteWriter() {
		log.info("=======================> delete");
		return list -> {
			for (Matching m : list) {
				matchTrie.pushMatching(m);
			}
		};
	}

}
