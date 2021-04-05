package com.studyhelper.domain.matching.service;

import java.util.List;
import java.util.Optional;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ProduceMatching {
	private static final String TOPIC = "studyhelper";
	private final KafkaTemplate<String, Matching> kafkaTemplate;
	private final MatchingRepository matchingRepository;
	
	public void sendMatching(Matching matching) {
		log.info("매칭 정보 전달 "+matching.getSeq());
		
		if (isSameMatching(matching)) {
			log.info("이미 있는 매칭입니다 "+matching.getMemberId()+"님의 요청");
			return;
		}
		Matching savedMatching = matchingRepository.save(matching);
		
		log.info("매칭요청 -->  " + "요청아이디: " + savedMatching.getMemberId() + " 지역: " + savedMatching.getRegion() + " 주제: "
				+ savedMatching.getSubject() + " 인원: " + savedMatching.getSize());
		
		
		kafkaTemplate.send(TOPIC,savedMatching);
	}
	
	public boolean isSameMatching(Matching matching) {
		Optional<Matching> sameMatching =
				matchingRepository
				.findByMemberIdAndSizeAndRegionAndSubject(matching.getMemberId(), matching.getSize(), matching.getRegion().toString(), matching.getSubject().toString());
	
		if (sameMatching.isPresent()) {
			return true;
		}
		return false;
	}
}
