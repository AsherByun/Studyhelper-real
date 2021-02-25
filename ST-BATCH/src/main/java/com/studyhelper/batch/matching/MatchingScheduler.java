package com.studyhelper.batch.matching;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.studyhelper.batch.domain.matching.MatchingRepository;
import com.studyhelper.batch.domain.matching.MatchingService;
import com.studyhelper.batch.domain.member.MemberRepository;
import com.studyhelper.batch.matching.algorithm.MatchTrie;
import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class MatchingScheduler {
	private final MatchingRepository matchingRepository;
	private final MatchingService matchingService;
	private final MemberRepository memberRepo;
	private final MatchTrie matchTrie;
	
	@Scheduled(fixedDelay = 1000000)
	public void runMatching() {
		matchTrie.initSetting();
		//전체 매칭 리스트 불러오기
		List<Matching> matchings =matchingRepository.findAll();
		//매칭
		for(Matching matching:matchings) {
			Optional<Team> team = matchTrie.pushMatching(matching);
			if (team.isPresent()) {
				log.info("매칭이 완료됐습니다 "+team.get().getTeamName());
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
