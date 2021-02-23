package com.studyhelper.domain.member;

import java.util.List;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.MemberTeam;
import com.studyhelper.domain.entity.Team;

public interface MemberService {
	//중복된 아이디, 닉네임이 있는지 확인
	boolean checkIdAndNickName(Member member);
	//회원가입 저장
	Member saveMember(Member member);
	//맴버가 속해있는 팀들을 가져온다
	List<Team> findMemberTeamsById(String id);
	//멤버가 팀을 매칭했을때 저장하는 서비스
	MemberTeam saveMemberTeam(Member member, Team team);
	//멤버가 매칭을 신청했을 경우
	Matching saveMatching(Member member,Matching matching);
	//멤버가 신청한 매칭들을 가져오는 것
	List<Matching> findMatchingsById(String id);
}
