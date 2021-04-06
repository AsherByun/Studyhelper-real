package com.studyhelper.domain.matching.service.schedule.match.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.entity.enums.Region;
import com.studyhelper.domain.matching.entity.enums.Subject;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.repo.MemberRepository;
import com.studyhelper.domain.member.service.MemberService;
import com.studyhelper.domain.team.entity.MemberTeam;
import com.studyhelper.domain.team.entity.Team;
import com.studyhelper.domain.team.repo.MemberTeamRepository;
import com.studyhelper.domain.team.repo.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
/*
 * 매칭을 도와주는 클래스 -> trie와 비슷한 구조 -> 옵션이 많아지면 ㄹㅇ 트라이로 구현
 * 같은 매칭일 경우 처리 ->
 */
@Configuration
@Slf4j
@RequiredArgsConstructor
public class Matcher {
	public static final String BASE_TEAM_NAME = "BASE";

	private final int REGION_LENGTH = Region.values().length;
	private final int SUBJECT_LENGTH = Subject.values().length;
	private final int MAX_SIZE = 6;

	private final MatchingRepository matchingRepository;
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	private final MemberService memberService;

	public static HashSet<Matching>[][][] matchs;
	public ConvertHashMap convert;
	
	public void initSetting() {
		convert = new ConvertHashMap();
		matchs = new HashSet[REGION_LENGTH][SUBJECT_LENGTH][MAX_SIZE];
		setMatchs();
	}
	//매칭들을 모아놓을 리스트 초기화
	public void setMatchs() {
		for (int i = 0; i < REGION_LENGTH; i++) {
			for (int j = 0; j < SUBJECT_LENGTH; j++) {
				for (int k = 0; k < MAX_SIZE; k++) {
					matchs[i][j][k] = new HashSet<Matching>();
				}
			}
		}
	}
	//매칭을 넣어주고 size가 맞으면 팀을 생성
	@Async
	@Transactional
	public void pushMatching(Matching matching) {
		log.info("매칭 알고리즘 시작!!!");
		
		int subjectNum = convert.converting(matching.getSubject().toString());
		int regionNum = convert.converting(matching.getRegion().toString());
		int sizeNum = matching.getSize();
		
		Set<Matching> makingTeamMatchs = null;
		
		//매칭을 넣고, 매칭팀을 구성하는 부분은 동기화 시켜줘야한다.
		synchronized (this) {
			matchs[regionNum][subjectNum][sizeNum].add(matching);
			if (matchs[regionNum][subjectNum][sizeNum].size() == sizeNum) {
				makingTeamMatchs = (HashSet<Matching>) matchs[regionNum][subjectNum][sizeNum].clone();
				//팀을 만들 매칭들을 모아주고, 현재 매칭은 비워준다.
				matchs[regionNum][subjectNum][sizeNum] = new HashSet<Matching>();
			} else {
				return;
			}
		}
		
		makeTeamByMatching(makingTeamMatchs);
	}
	
	@Async
	private void makeTeamByMatching(Set<Matching> matchings) {
		StringBuffer teamMemberForLog = new StringBuffer("팀 매칭 : ");
		
		//새로운 팀생성
		Team team =new Team();
		team.setTeamName(BASE_TEAM_NAME);
		teamRepository.save(team);
		
		for (Matching matching : matchings) {
			//회원 탈퇴시 매칭정보도 삭제해줘야함
			Optional<Member> member = memberRepository.findById(matching.getMemberId());

			//로깅
			teamMemberForLog.append(member.get().getId()+" ");
			
			MemberTeam memberTeam = memberService.matchingTeamByMembers(member.get(), team);
			matchingRepository.delete(matching);
		}
		log.info(teamMemberForLog.toString()+" 님들의 매칭이 성공했습니다! 팀 SEQ: "+team.getSeq());
	}
}
