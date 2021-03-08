package com.studyhelper.domain.member.service;

import java.util.List;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.team.entity.MemberTeam;
import com.studyhelper.domain.team.entity.Team;

public interface MemberService {
	//멤버가 신청한 매칭들을 가져오는 것
	List<Matching> findMatchingsById(String id);
	//멤버 팀 매칭시켜줌
	MemberTeam matchingTeamByMembers(Member member,Team team);
}
