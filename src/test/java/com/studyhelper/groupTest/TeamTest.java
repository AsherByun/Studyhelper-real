package com.studyhelper.groupTest;

import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.studyhelper.entity.Team;
import com.studyhelper.entity.Member;
import com.studyhelper.entity.MemberTeam;
import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.properties.Gender;
import com.studyhelper.properties.Role;
import com.studyhelper.team.dao.MemberTeamRepository;
import com.studyhelper.team.dao.TeamRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TeamTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	private TeamRepository teamRepository;
	@Autowired
	private MemberTeamRepository memberTeamRepository;
	
	@Test
	public void groupInputTest() {
		Member member = new Member();
		member.setAge(26);
		member.setGender(Gender.MAN);
		member.setId("dfdo");
		member.setName("sungjae");
		member.setNickName("sdf");
		member.setPassword(encoder.encode("asd"));
		member.setRole(Role.ROLE_ADMIN);
		
		Team team = new Team();
		MemberTeam memberTeam = new MemberTeam();
		
		memberTeam.setMember(member);
		memberTeam.setTeam(team);
		//순서 중요 member - team 부터 영속시켜야된다
		memberRepository.save(member);
		teamRepository.save(team);
		
		memberTeamRepository.save(memberTeam);
		
		Member gMember = memberRepository.findById("dfdo").get();
		System.out.println(gMember.getMemberTeams().size());
		System.out.println(gMember.getMemberTeams().get(0).getTeam().getSeq());
		
		
	}

}
