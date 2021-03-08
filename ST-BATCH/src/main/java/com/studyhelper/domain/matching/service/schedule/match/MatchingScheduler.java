package com.studyhelper.domain.matching.service.schedule.match;

import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.matching.service.schedule.match.algorithm.MatchTrie;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchingScheduler {
	private final MatchingRepository matchingRepository;
	private final MatchTrie matchTrie;
	private final ChatRoomRepository chatRoomRepository;

	@Scheduled(fixedDelay = 60000) // 60초에 한번씩 실행
	public void runMatching() {
		log.info("매칭 시작!!!!");

		matchTrie.initSetting();
		// 전체 매칭 리스트 불러오기
		List<Matching> matchings = matchingRepository.findAll();

		// 매칭 뒤에서부터 시작
		for (int i = matchings.size() - 1; i >= 0; i--) {
			Matching matching = matchings.get(i);
			Optional<Team> team = matchTrie.pushMatching(matching);
			if (team.isPresent()) {
				// 팀이 생성됐다면 채팅방을 맵핑해줘야함
				Team newTeam = team.get();
				ChatRoom chatRoom = chatRoomRepository.createChatRoom(newTeam.getTeamName());
				newTeam.setChatRoomId(chatRoom.getRoomId());
			}
		}
	}
}
