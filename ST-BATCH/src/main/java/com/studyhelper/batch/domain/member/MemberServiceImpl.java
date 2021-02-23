package com.studyhelper.batch.domain.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.batch.domain.matching.MatchingRepository;
import com.studyhelper.batch.domain.team.MemberTeamRepository;
import com.studyhelper.batch.domain.team.TeamRepository;
import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.MemberTeam;
import com.studyhelper.domain.entity.Team;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	public final MemberRepository memberRepository;
	public final MemberTeamRepository memberTeamRepository;
	public final TeamRepository teamRepository;
	public final MatchingRepository matchRepository;

	@Transactional(readOnly = true)
	@Override
	public List<Team> findMemberTeamsById(String id) {
		Optional<Member> memberOptional = memberRepository.findById(id);
		// null값대신 여기서 try catch나 exception 설정해주도록하자
		if (!memberOptional.isPresent()) {
			return null;
		}
		Member member = memberOptional.get();
		List<Team> teams = new ArrayList<Team>();

		for (MemberTeam memberTeam : member.getMemberTeams()) {
			Team team = memberTeam.getTeam();
			teams.add(team);
		}

		return teams;
	}

	@Transactional
	@Override
	public MemberTeam saveMemberTeam(Member member, Team team) {
		MemberTeam memberTeam = new MemberTeam();
		memberTeam.setMember(member);
		memberTeam.setTeam(team);
		
		memberTeamRepository.save(memberTeam);
		return memberTeam;
	}

	@Transactional
	@Override
	public Matching saveMatching(Member member, Matching matching) {
		matching.setMember(member);
		matchRepository.save(matching);

		return matching;
	}

	@Transactional
	@Override
	public List<Matching> findMatchingsById(String id) {
		Optional<Member> memberOptional = memberRepository.findById(id);

		if (!memberOptional.isPresent()) {
			return null;
		}
		Member member = memberOptional.get();

		List<Matching> matchings = new ArrayList<Matching>();
		for (Matching matching : member.getMatchs()) { // n+1 문제.
			matchings.add(matching);
		}

		return matchings;
	}

}
