package com.studyhelper.test;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.entity.MemberTeam;
import com.studyhelper.entity.Team;
import com.studyhelper.enums.Gender;
import com.studyhelper.enums.Region;
import com.studyhelper.enums.Role;
import com.studyhelper.enums.Subject;
import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.member.service.MatchingService;
import com.studyhelper.team.dao.MatchingRepository;
import com.studyhelper.team.dao.MemberTeamRepository;
import com.studyhelper.team.dao.TeamRepository;

@RestController
public class DBTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MemberTeamRepository memberTeamRepository;
	@Autowired
	private MatchingRepository matchRepository;
	@Autowired
	private MatchingService matchingService;

	@GetMapping("/testing/matchs")
	public List<Matching> testMatch() {
//		List<Matching> lists = matchRepository.searchAllMatchings();
//		Matching matching =new Matching();
//		matching.setRegion(Region.SEOUL_GANGNAM);
//		matching.setRequestMatchingDate(new Date());
//		matching.setSize(4);
//		matching.setSubject(Subject.PROGRAMMING_C);
//		
//		matchingService.insertMatching(matching);
		List<Matching> lists = matchRepository.searchAllMatchings();
		return lists;
	}

	@GetMapping("/testing/match")
	public String testMatch(String id) {
		Member member = memberRepository.findMemberById(id).get();

		Matching match = new Matching();
		match.setRegion(Region.SEOUL_GANGNAM);
		match.setSize(4);
		match.setSubject(Subject.PROGRAMMING_C);
		match.setMember(member);

		memberRepository.save(member);
		matchRepository.save(match);

		return "aaaaaaaaaaaaaaaaaaaaaaa";
	}

	@GetMapping("/testing")
	public String testing(String id) {
		Member member = new Member();
		member.setAge(24);
		member.setGender(Gender.MAN);
		member.setId(id);
		member.setName(id);
		member.setPassword(encoder.encode("asdf"));
		member.setRole(Role.ROLE_ADMIN);
		member.setNickName(id);

		Team team = new Team();

		MemberTeam memberTeam = new MemberTeam();

		memberTeam.setMember(member);
		memberTeam.setTeam(team);

		memberRepository.save(member);
		teamRepository.save(team);
		memberTeamRepository.save(memberTeam);

		return "aaaaaaa";
	}

	@GetMapping("/testing/getmemberTest")
	public String testc(String id) {
		Member member = memberRepository.findMemberById(id).get();

		System.out.println(member.getMemberTeams().size());
		for (MemberTeam mt : member.getMemberTeams()) {
			System.out.println(mt.getSeq());
			System.out.println(mt.getTeam().getSeq());
		}
		System.out.println("--------------------------");

		for (Matching match : member.getMatchs()) {
			System.out.println(match.getRegion().toString());
		}
		return "aaaaaaaaaaa";
	}
}
