package com.studyhelper.web.controller;

import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.member.MemberService;
import com.studyhelper.member.security.SecurityUser;

@Controller
public class TeamMatcingController {

	public MemberService memberService;

	public TeamMatcingController(MemberService memberService) {
		this.memberService = memberService;
	}

	@GetMapping("/matching/request")
	public String getMatching() {
		return "matchForm";
	}

	@PostMapping("/matching/request")
	public String postMatching(@AuthenticationPrincipal SecurityUser securityUser, Matching matching) {
		Member member = securityUser.getMember();
		matching.setRequestMatchingDate(new Date());
		
		memberService.saveMatching(member, matching);
		
		return "userpage";
	}
}
