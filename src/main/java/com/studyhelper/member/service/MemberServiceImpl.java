package com.studyhelper.member.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.entity.MemberTeam;
import com.studyhelper.entity.Team;
import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.team.dao.MatchRepository;
import com.studyhelper.team.dao.MemberTeamRepository;
import com.studyhelper.team.dao.TeamRepository;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private PasswordEncoder encoder;

	public final MemberRepository memberRepo;
	public final MemberTeamRepository memberTeamRepository;
	public final TeamRepository teamRepository;
	public final MatchRepository matchRepository;

	public MemberServiceImpl(MemberRepository meberRepo, TeamRepository teamRepository,
			MemberTeamRepository memberTeamRepository, MatchRepository matchRepository) {
		this.memberRepo = meberRepo;
		this.teamRepository = teamRepository;
		this.memberTeamRepository = memberTeamRepository;
		this.matchRepository = matchRepository;
	}

	@Override
	public boolean checkIdAndNickName(Member member) {

		return memberRepo.isSameIdOrNickName(member.getNickName(), member.getId()) == 1;
	}

	@Override
	public Member saveMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepo.save(member);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Team> findMemberTeamsById(String id) {
		Optional<Member> memberOptional = memberRepo.findById(id);
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
		Optional<Member> memberOptional = memberRepo.findById(id);

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
