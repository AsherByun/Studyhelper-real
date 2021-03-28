package com.studyhelper.domain.matching.service.schedule.match;

import java.io.IOException;
import java.util.Optional;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.service.MatchingService;
import com.studyhelper.domain.matching.service.schedule.match.algorithm.MatchTrie;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class KafkaMatchingConsumer {
	private static final String TOPIC = "studyhelper";
	private static final String GROUP_ID = "batch1";
	private final MatchTrie matchTrie;
	private final MatchingService matchingService;
	
	@KafkaListener(topics = TOPIC,groupId = GROUP_ID,containerFactory = "matchListener")
	public void consume(Matching matching) throws IOException{
		log.info("새로운 매칭 도착"+matching.getMemberId());
		
		Optional<Team> team = matchTrie.pushMatching(matching);
		if (team.isPresent()) {
			// 팀이 생성됐다면 채팅방을 맵핑해줘야함
			matchingService.mappingChatRoom(team.get());
		}
	}
}
