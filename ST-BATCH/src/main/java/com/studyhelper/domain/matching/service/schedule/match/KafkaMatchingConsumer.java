package com.studyhelper.domain.matching.service.schedule.match;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.matching.service.MatchingService;
import com.studyhelper.domain.matching.service.schedule.match.algorithm.Matcher;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMatchingConsumer {
	private static final String TOPIC = "studyhelper";
	private static final String GROUP_ID = "batch1";
	private final Matcher matchTrie;
	
	@KafkaListener(topics = TOPIC,groupId = GROUP_ID,containerFactory = "matchListener")
	public void consume(Matching matching) throws IOException{
		log.info("새로운 매칭 도착"+matching.getMemberId());
		
		//비동기적-논블러킹으로 실행
		matchTrie.pushMatching(matching);
	}
}
