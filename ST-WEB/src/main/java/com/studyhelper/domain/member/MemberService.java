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
	List<Team> findMemberTeamsById(Member member);
	
}
