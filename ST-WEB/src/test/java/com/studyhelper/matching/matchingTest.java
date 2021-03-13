package com.studyhelper.matching;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.studyhelper.StudyhelperTest;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.entity.enums.Gender;
import com.studyhelper.domain.matching.entity.enums.Region;
import com.studyhelper.domain.matching.entity.enums.Subject;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.entity.enums.Role;
import com.studyhelper.domain.member.repo.MemberRepository;
import com.studyhelper.domain.member.security.MemberDetailService;
import com.studyhelper.domain.member.service.MemberService;
import com.studyhelper.member.login.LoginTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(properties = {
		"spring.config.location=C:/Users/Owner/eclipse-workspace/config/application-real.yml" }, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
@AutoConfigureMockMvc
public class matchingTest {
	@Autowired
	MockMvc mvc;
	@Autowired
	private WebApplicationContext ctx;
	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberRepository memberRepository;
	@Autowired
	private MemberDetailService memberDetailService;
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
	@Transactional
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(ctx).addFilters(new CharacterEncodingFilter("UTF-8", true))
				.alwaysDo(print()).build();
		Member member = new Member();
		member.setAge(22);
		member.setGender(Gender.MAN);
		member.setId("dfdo333");
		member.setName("sungjae");
		member.setNickName("sung");
		member.setPassword("1234");
		member.setRole(Role.ROLE_MEMBER);
		memberService.saveMember(member);
	}
	
	@Test
	@Transactional
	void 회원가입확인() {
		Member member = memberRepository.findById("dfdo333").get();
		System.out.println(member.getId());
	}
	
	@Test
	@Transactional
	@WithUserDetails(value = "king0", userDetailsServiceBeanName = "memberDetailService")
	void 매칭테스트() throws Exception {
		Matching matching = new Matching();
		matching.setRegion(Region.SEOUL_SHINCHON);
		matching.setSize(4);
		matching.setSubject(Subject.PROGRAMMING_C);
		String content = objectMapper.writeValueAsString(matching);
		
		mvc.perform(post("/matching/request").content(content).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)).andExpect(status().is3xxRedirection()).andDo(print());
	}
//	@Test
//	@Transactional
//	void 회원가입() throws Exception {
//		Member member = new Member();
//		member.setAge(22);
//		member.setGender(Gender.MAN);
//		member.setId("dfdo1995");
//		member.setName("sungjaebyeun");
//		member.setNickName("byeun");
//		member.setPassword("1234");
//		String content = objectMapper.writeValueAsString(member);
//		
//		System.out.println(content);
//		
//		mvc.perform(post("/signup").content(content).contentType(MediaType.APPLICATION_JSON)
//				.accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andDo(print());
//	}
}
