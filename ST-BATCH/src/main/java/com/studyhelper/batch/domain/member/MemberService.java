package com.studyhelper.batch.domain.member;

import java.util.List;

import com.domain.entity.Matching;
import com.domain.entity.Member;
import com.domain.entity.MemberTeam;
import com.domain.entity.Team;

public interface MemberService {
	//맴버가 속해있는 팀들을 가져온다
	List<Team> findMemberTeamsById(String id);
	//멤버가 팀을 매칭했을때 저장하는 서비스
	MemberTeam saveMemberTeam(Member member, Team team);
	//멤버가 매칭을 신청했을 경우
	Matching saveMatching(Member member,Matching matching);
	//멤버가 신청한 매칭들을 가져오는 것
	List<Matching> findMatchingsById(String id);
}
