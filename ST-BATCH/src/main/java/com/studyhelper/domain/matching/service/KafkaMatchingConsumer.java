package com.studyhelper.domain.matching.service;

import java.io.IOException;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.matching.entity.Matching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMatchingConsumer {
	private static final String TOPIC = "studyhelper";
	private static final String GROUP_ID = "batch1";
	
	@KafkaListener(topics = TOPIC,groupId = GROUP_ID,containerFactory = "matchListener")
	public void consume(Matching matching) throws IOException{
		log.info("새로운 매칭 도착"+matching.getMemberId());
	}
}
