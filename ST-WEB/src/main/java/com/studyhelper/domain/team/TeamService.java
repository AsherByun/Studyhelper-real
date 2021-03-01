package com.studyhelper.domain.team;

import java.util.List;

import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;

public interface TeamService{
	//같은 팀에있는 멤버들 다뽑아오기
	List<Member> findMembersSameTeams(Team team);
	//팀에 채팅방 아이디 넣기
	Team saveTeamChatId(Team team,ChatRoom chatRoom);
	
}
