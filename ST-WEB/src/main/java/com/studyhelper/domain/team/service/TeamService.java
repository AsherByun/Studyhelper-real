package com.studyhelper.domain.team.service;

import java.util.List;

import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.team.entity.Team;

public interface TeamService{
	//같은 팀에있는 멤버들 다뽑아오기
	List<Member> findMembersSameTeams(Team team);
	//팀에 채팅방 아이디 넣기
	Team saveTeamChatId(Team team,ChatRoom chatRoom);
	//팀 seq로 팀을 뽑아오기
   	Team findTeam(Team team);
   	//팀이름 변경
   	void changeTeamName(Team team,String changeTeamName);
}
