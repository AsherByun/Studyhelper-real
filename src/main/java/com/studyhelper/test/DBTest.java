package com.studyhelper.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyhelper.entity.Member;
import com.studyhelper.entity.MemberTeam;
import com.studyhelper.entity.Team;
import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.properties.Gender;
import com.studyhelper.properties.Role;
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

	@GetMapping("/testing")
	public String testing() {
		Member gMember = memberRepository.findById("dfdo").get();
		System.out.println(gMember.getMemberTeams().size());
		System.out.println(gMember.getMemberTeams().get(0).getSeq());
		System.out.println("asd");

		return "aaaaaaa";
	}
}
