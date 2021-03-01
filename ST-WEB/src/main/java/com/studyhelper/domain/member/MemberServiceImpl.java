package com.studyhelper.domain.member;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.MemberTeam;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.matching.MatchingRepository;
import com.studyhelper.domain.team.MemberTeamRepository;
import com.studyhelper.domain.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private PasswordEncoder encoder;

	public final MemberRepository memberRepository;
	public final MemberTeamRepository memberTeamRepository;
	public final TeamRepository teamRepository;
	public final MatchingRepository matchRepository;

	@Transactional
	@Override
	public boolean checkIdAndNickName(Member member) {

		return memberRepository.isSameIdOrNickName(member.getNickName(), member.getId()) == 1;
	}
	@Transactional
	@Override
	public Member saveMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepository.save(member);
	}

	@Transactional(readOnly = true)
	@Override
	public List<Team> findMemberTeamsById(Member member) {
		List<Team> teams = new ArrayList<Team>();

		for (MemberTeam memberTeam : member.getMemberTeams()) {
			Team team = memberTeam.getTeam();
			teams.add(team);
		}

		return teams;
	}

}
