package com.studyhelper.domain.team;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.MemberTeam;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class TeamServiceImpl implements TeamService{
	private final MemberRepository memberRepository;
	private final TeamRepository teamRepositoy;
	
	@Transactional
	@Override
	public List<Member> findMembersSameTeams(Team team) {
		List<Member> members = new ArrayList<Member>();
		team = teamRepositoy.findById(team.getSeq()).get();
		
		for(MemberTeam memberTeam:team.getMemberTeams()) {
			Member member = memberTeam.getMember();
			members.add(member);
			log.info(member.getId()+" 추가");
		}
		
		return members;
	}

}
