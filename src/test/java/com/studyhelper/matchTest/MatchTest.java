package com.studyhelper.matchTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.enums.Gender;
import com.studyhelper.enums.Region;
import com.studyhelper.enums.Role;
import com.studyhelper.enums.Subject;
import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.team.dao.MatchRepository;
import com.studyhelper.team.dao.MemberTeamRepository;
import com.studyhelper.team.dao.TeamRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class MatchTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MemberTeamRepository memberTeamRepository;
	@Autowired
	private MatchRepository matchRepository;

	@Test
	public void matchTest() {
		Member member = new Member();
		member.setAge(26);
		member.setGender(Gender.MAN);
		member.setId("dfdo3");
		member.setName("sungjae");
		member.setNickName("sdf");
		member.setPassword(encoder.encode("asd"));
		member.setRole(Role.ROLE_ADMIN);
		
		Matching match = new Matching();
		match.setRegion(Region.SEOUL_GANGNAM);
		match.setSize(4);
		match.setSubject(Subject.PROGRAMMING_C);
		match.setMember(member);
		
		
//		memberRepository.save(member); //왜?
//		matchRepository.save(match);
//		
//		Member gMember = memberRepository.findMemberById("dfdo3").get();
//		System.out.println(gMember.getMatchs().size());
	}
}
