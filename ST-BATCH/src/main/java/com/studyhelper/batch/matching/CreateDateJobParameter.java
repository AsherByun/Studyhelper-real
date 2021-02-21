package com.studyhelper.batch.matching;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@NoArgsConstructor
public class CreateDateJobParameter {
	
	private LocalDate createDate;
	
	@Value("#{jobParameters[createDate]}")
	public void setCreateDate(String createDate) {
		this.createDate = LocalDate.parse(createDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
	}
}
