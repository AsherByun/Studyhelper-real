package com.studyhelper.domain.team.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.repo.MemberRepository;
import com.studyhelper.domain.team.entity.MemberTeam;
import com.studyhelper.domain.team.entity.Team;
import com.studyhelper.domain.team.repo.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepository;
	
	@Transactional
	@Override
	public List<Member> findMembersSameTeams(Team team) {
		List<Member> members = new ArrayList<Member>();
		team = teamRepository.findById(team.getSeq()).get();
		
		for(MemberTeam memberTeam:team.getMemberTeams()) {
			Member member = memberTeam.getMember();
			members.add(member);
		}
		Collections.sort(members, new Comparator<Member>() {
			@Override
			public int compare(Member o1, Member o2) {
				return o1.getId().compareTo(o2.getId());
			}
		});;
		
		return members;
	}
	
	@Transactional
	@Override
	public Team saveTeamChatId(Team team, ChatRoom chatRoom) {
		team.setChatRoomId(chatRoom.getRoomId());
		
		return team;
	}
	
	@Transactional
	@Override
	public Team findTeam(Team team) {

		return teamRepository.findById(team.getSeq()).get();
	}
	
	@Transactional
	@Override
	public void changeTeamName(Team team, String changeTeamName) {
		team = teamRepository.findById(team.getSeq()).get();
		
		team.setTeamName(changeTeamName);
	}

}
