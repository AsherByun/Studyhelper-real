package com.studyhelper.domain.matching.service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.matching.entity.Matching;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProduceMatching {
	private static final String TOPIC = "studyhelper";
	private final KafkaTemplate<String, Matching> kafkaTemplate;
	
	public void sendMatching(Matching matching) {
		log.info("매칭 정보 전달 "+matching.getSeq());
		kafkaTemplate.send(TOPIC,matching);
	}
}
