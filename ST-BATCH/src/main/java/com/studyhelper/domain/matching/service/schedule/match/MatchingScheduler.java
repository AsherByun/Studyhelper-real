package com.studyhelper.domain.matching.service.schedule.match;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.matching.service.MatchingService;
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
	private final MatchingService matchingService;

	// 서버 시작시 한번 실행 --> redis에 올리는 작업, matchs 초기화
	@PostConstruct
	public void runMatching() {
		log.info("매칭서버 처음시작 ---> 매칭 및 리스트에 저장하기");

		matchTrie.initSetting();
		// 전체 매칭 리스트 불러오기
		List<Matching> matchings = matchingRepository.findAll();

		// 매칭 뒤에서부터 시작
		for (int i = matchings.size() - 1; i >= 0; i--) {
			Matching matching = matchings.get(i);
			
			Optional<Team> team = matchTrie.pushMatching(matching);
		}
	}
}
