package com.studyhelper.groupTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.studyhelper.dto.Group;
import com.studyhelper.dto.Member;
import com.studyhelper.member.entity.MemberRepository;
import com.studyhelper.properties.Gender;
import com.studyhelper.properties.Role;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GroupTest {
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Test
	public void groupInputTest() {
	}

}
