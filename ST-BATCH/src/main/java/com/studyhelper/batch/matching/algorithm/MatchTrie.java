package com.studyhelper.batch.matching.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.domain.entity.Matching;
import com.domain.entity.Member;
import com.domain.entity.Team;
import com.domain.enums.Region;
import com.domain.enums.Subject;
import com.studyhelper.batch.domain.matching.MatchingRepository;
import com.studyhelper.batch.domain.member.MemberRepository;
import com.studyhelper.batch.domain.member.MemberService;
import com.studyhelper.batch.domain.team.MemberTeamRepository;
import com.studyhelper.batch.domain.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class MatchTrie {
	public static final String BASE_TEAM_NAME = "BASE";

	public final ConvertHashMap convert;
	private final int REGION_LENGTH = Region.values().length;
	private final int SUBJECT_LENGTH = Subject.values().length;
	private final int MAX_SIZE = 6;
	
	@Autowired
	private MatchingRepository matchingRepository;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MemberTeamRepository memberTeamRepository;
	@Autowired
	private MemberService memberService;

	public List<Matching>[][][] matchs;

	public MatchTrie() {
		convert = new ConvertHashMap();
		matchs = new ArrayList[REGION_LENGTH][SUBJECT_LENGTH][MAX_SIZE];
		setMatchs();
	}

	public void setMatchs() {
		for (int i = 0; i < REGION_LENGTH; i++) {
			for (int j = 0; j < SUBJECT_LENGTH; j++) {
				for (int k = 0; k < MAX_SIZE; k++) {
					matchs[i][j][k] = new ArrayList<Matching>();
				}
			}
		}
	}

	public void pushMatching(Matching match) {
		int subjectNum = convert.converting(match.getSubject().toString());
		int regionNum = convert.converting(match.getRegion().toString());
		int sizeNum = match.getSize();

		matchs[regionNum][subjectNum][sizeNum].add(match);
		if (matchs[regionNum][subjectNum][sizeNum].size() == sizeNum) {
			Team team = new Team();
			team.setTeamName(BASE_TEAM_NAME);
			teamRepository.save(team);
			for (Matching matching : matchs[regionNum][subjectNum][sizeNum]) {
				Member member = matching.getMember();
				
				memberService.saveMemberTeam(member, team);
				matchingRepository.delete(matching);
			}
			
			matchs[regionNum][subjectNum][sizeNum] = new ArrayList<Matching>(); // 삭제시켜야된다 --디비도접근
		}
	}
}
