package com.studyhelper.batch.matching;

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

import com.domain.entity.Matching;
import com.studyhelper.batch.matching.algorithm.MatchTrie;

import java.util.Iterator;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MatchingJobConfig {
	public static final String JOB_NAME = "matchingJob";

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

	@Bean(name = JOB_NAME)
	public Job matchingJob() {
		return jobBuilderFactory.get(JOB_NAME).start(matchingStep()).build();
	}

	@Bean(name = JOB_NAME + "_makeTeamStep")
	@JobScope
	public Step matchingStep() {
		return stepBuilderFactory.get(JOB_NAME + "_makeTeamStep").<Matching, Matching>chunk(chunkSize)
				.reader(makeTeamReader()).writer(makeTeamWriter()).build();
	}

	@Bean(name = JOB_NAME + "_makeTeamReader")
	@StepScope
	public JpaPagingItemReader<Matching> makeTeamReader() {
		return new JpaPagingItemReaderBuilder<Matching>().name(JOB_NAME + "_makeTeamReader")
				.entityManagerFactory(entityManagerFactory).pageSize(chunkSize).queryString("SELECT m FROM Matching m")
				.build();
	}

	public ItemWriter<Matching> makeTeamWriter() {
		return list -> {
			for (Matching m : list) {
				matchTrie.pushMatching(m);
			}
		};
	}
}
