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

import java.util.Iterator;

import javax.persistence.EntityManagerFactory;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MatchingJobConfig {
	public static final String JOB_NAME = "matchingJob";
	public static final String BASE_TEAM_NAME = "BASE";
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	private final EntityManagerFactory entityManagerFactory;
	
	private int chunkSize;

	@Value("${chunkSize:100}")
	public void setChunkSize(int chunkSize) {
		this.chunkSize = chunkSize;
	}
	
	@Bean(name = JOB_NAME)
	public Job matchingJob() {
		return jobBuilderFactory.get(JOB_NAME)
				.start(matchingStep()).incrementer(new RunIdIncrementer()).build();
	}
	
	@Bean(name = JOB_NAME + "_makeTeamStep")
	@JobScope
	public Step matchingStep() {
		return stepBuilderFactory.get(JOB_NAME + "_makeTeamStep").<Member, Member>chunk(chunkSize).reader(makeTeamReader())
				.processor(makeTeamProcessor()).writer(makeTeamWriter()).build();
	}

	@Bean(name = JOB_NAME + "_makeTeamReader")
	@StepScope
	public JpaPagingItemReader<Member> makeTeamReader() {
		return new JpaPagingItemReaderBuilder<Member>().name(JOB_NAME + "_makeTeamReader")
				.entityManagerFactory(entityManagerFactory).pageSize(chunkSize).queryString("SELECT m FROM Member m")
				.build();
	}

	public ItemProcessor<Member, Member> makeTeamProcessor() {
		return member -> {
			return member;
		};
	}

	public ItemWriter<Member> makeTeamWriter() {
		return list -> {
			
		};
	}
}
