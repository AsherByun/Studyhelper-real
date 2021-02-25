package com.studyhelper.domain.team;

import java.util.List;

import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;

public interface TeamService{
	//같은 팀에있는 멤버들 다뽑아오기
	List<Member> findMembersSameTeams(Team team);
}
