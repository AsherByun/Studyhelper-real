package com.studyhelper.batch.domain.matching.service.deleteSchedule;
//package com.studyhelper.batch.matching;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import com.studyhelper.batch.domain.matching.MatchingRepository;
//import com.studyhelper.batch.matching.algorithm.MatchTrie;
//import com.studyhelper.domain.entity.Matching;
//
//import java.time.LocalDate;
//import java.time.ZoneId;
//import java.util.Date;
//import java.util.Iterator;
//
//import javax.persistence.EntityManagerFactory;
//
//@Slf4j
//@RequiredArgsConstructor
//@Configuration
//public class DeleteMatchingJobConfig {
//	public static final String JOB_NAME = "DeleteMatchingJob";
//
//	private final MatchingRepository matchingRepository;
//	private final JobBuilderFactory jobBuilderFactory;
//	private final StepBuilderFactory stepBuilderFactory;
//	private final EntityManagerFactory entityManagerFactory;
//	private int chunkSize;
//
//	@Value("${chunkSize:100}")
//	public void setChunkSize(int chunkSize) {
//		this.chunkSize = chunkSize;
//	}
//
//	@Bean(name = JOB_NAME)
//	public Job deleteMatchingJob() {
//		return jobBuilderFactory.get(JOB_NAME)
//				.start(deleteMatching(null))
//				.build();
//	}
//
//	@Bean(name = JOB_NAME + "_deleteMatchingStep")
//	@JobScope
//	public Step deleteMatching(@Value("#{jobParameters[time]}") String time) {
//		return stepBuilderFactory.get(JOB_NAME + "_deleteMatchingStep").<Matching, Matching>chunk(chunkSize)
//				.reader(deleteMatchingReader()).writer(deleteWriter()).build();
//	}
//
//	@Bean(name = JOB_NAME + "_deleteMatchingReader")
//	@StepScope
//	public JpaPagingItemReader<Matching> deleteMatchingReader() {
//		
//		JpaPagingItemReader<Matching> reader = new JpaPagingItemReader<Matching>() {
//			@Override
//			public int getPage() {
//				return 0;
//			}
//		};
//		
//		reader.setQueryString("SELECT m FROM Matching m");
//		reader.setPageSize(chunkSize);
//		reader.setEntityManagerFactory(entityManagerFactory);
//		reader.setName(JOB_NAME+ "_deleteMatchingReader");
//		
//		return reader;
//	}
//
//	public ItemWriter<Matching> deleteWriter() {
//		LocalDate today = LocalDate.now().minusDays(3);
//		return Matchings -> {
//			for(Matching matching : Matchings) {
//				LocalDate matchingDate = convertToLocalDate(matching.getRequestMatchingDate());
//				if (matchingDate.isBefore(today)) {
//					matchingRepository.delete(matching);
//				}
//			}
//		};
//	}
//	
//	public LocalDate convertToLocalDate(Date date) {
//		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	}
//}
