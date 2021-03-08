package com.studyhelper.batch.domain.matching.service.matchSchedule;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.studyhelper.batch.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.batch.domain.chat.dto.ChatRoom;
import com.studyhelper.batch.domain.matching.entity.Matching;
import com.studyhelper.batch.domain.matching.repo.MatchingRepository;
import com.studyhelper.batch.domain.matching.service.MatchingService;
import com.studyhelper.batch.domain.matching.service.matchSchedule.algorithm.MatchTrie;
import com.studyhelper.batch.domain.member.entity.Member;
import com.studyhelper.batch.domain.member.repo.MemberRepository;
import com.studyhelper.batch.domain.team.entity.Team;

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
		// 오래된 매칭부터 해주기 위해 sorting -> 날짜기준 -> aging
		Collections.sort(matchings, new Comparator<Matching>() {
			@Override
			public int compare(Matching o1, Matching o2) {
				return o1.getRequestMatchingDate().compareTo(o2.getRequestMatchingDate());
			}
		});

		// 매칭
		for (Matching matching : matchings) {
			Optional<Team> team = matchTrie.pushMatching(matching);
			if (team.isPresent()) {
				// 팀이 생성됐다면 채팅방을 맵핑해줘야함
				Team newTeam = team.get();
				ChatRoom chatRoom = chatRoomRepository.createChatRoom(newTeam.getTeamName());
				newTeam.setChatRoomId(chatRoom.getRoomId());
				log.info("매칭 성공 --> 매칭 정보: " + matching.getMemberId() + "의 매칭 성공");
				log.info("팀 생성 완료 " + team.get().getTeamName());
			}
		}
	}

//	@Scheduled(fixedDelay = 10000)
//	public void runMatching() {
//		List<Member> list = memberRepo.findAll();
//		for(Member member:list) {
//			log.info(member.getId()+" "+member.getNickName());
//		}
//	}
}
