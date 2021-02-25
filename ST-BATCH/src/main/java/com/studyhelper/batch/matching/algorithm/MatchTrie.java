package com.studyhelper.batch.matching.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.studyhelper.batch.domain.matching.MatchingRepository;
import com.studyhelper.batch.domain.member.MemberRepository;
import com.studyhelper.batch.domain.member.MemberService;
import com.studyhelper.batch.domain.team.MemberTeamRepository;
import com.studyhelper.batch.domain.team.TeamRepository;
import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.MemberTeam;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.enums.Region;
import com.studyhelper.domain.enums.Subject;

import lombok.RequiredArgsConstructor;
/*
 * 매칭을 도와주는 클래스 -> trie와 비슷한 구조 -> 옵션이 많아지면 ㄹㅇ 트라이로 구현
 * 같은 매칭일 경우 처리 ->
 */
@Configuration
@RequiredArgsConstructor
public class MatchTrie {
	public static final String BASE_TEAM_NAME = "BASE";

	private final int REGION_LENGTH = Region.values().length;
	private final int SUBJECT_LENGTH = Subject.values().length;
	private final int MAX_SIZE = 6;

	private final MatchingRepository matchingRepository;
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	private final MemberService memberService;

	public List<Matching>[][][] matchs;
	public ConvertHashMap convert;
	
	public void initSetting() {
		convert = new ConvertHashMap();
		matchs = new ArrayList[REGION_LENGTH][SUBJECT_LENGTH][MAX_SIZE];
		setMatchs();
	}
	//매칭들을 모아놓을 리스트 초기화
	public void setMatchs() {
		for (int i = 0; i < REGION_LENGTH; i++) {
			for (int j = 0; j < SUBJECT_LENGTH; j++) {
				for (int k = 0; k < MAX_SIZE; k++) {
					matchs[i][j][k] = new ArrayList<Matching>();
				}
			}
		}
	}
	//매칭을 넣어주고 size가 맞으면 팀을 생성
	public Optional<Team> pushMatching(Matching match) {
		int subjectNum = convert.converting(match.getSubject().toString());
		int regionNum = convert.converting(match.getRegion().toString());
		int sizeNum = match.getSize();
		
		//중복되는 아이디가 있으면 무시한다 + 삭제
		//이미 같은 매칭을 신청한게 있는지 확인함
		for(Matching matching:matchs[regionNum][subjectNum][sizeNum]) {
			if (matching.getMemberId().equals(match.getMemberId())) {
				return Optional.ofNullable(null);
			}
		}
		//아니면 매칭 추가
		matchs[regionNum][subjectNum][sizeNum].add(match);
		if (matchs[regionNum][subjectNum][sizeNum].size() == sizeNum) {
			Team team = new Team();
			team.setTeamName(BASE_TEAM_NAME);
			teamRepository.save(team);
			for (Matching matching : matchs[regionNum][subjectNum][sizeNum]) {
				Optional<Member> member = memberRepository.findById(matching.getMemberId());
				//회원 탈퇴시 매칭정보도 삭제해줘야함

				MemberTeam memberTeam = memberService.matchingTeamByMembers(member.get(), team);
				matchingRepository.delete(matching);
			}
			matchs[regionNum][subjectNum][sizeNum] = new ArrayList<Matching>();
			return Optional.ofNullable(team);
		}
		return Optional.ofNullable(null);
	}
	
}
