package com.studyhelper.member.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studyhelper.entity.Matching;
import com.studyhelper.entity.Member;
import com.studyhelper.member.security.SecurityUser;
import com.studyhelper.member.service.MemberService;

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
		memberService.saveMatching(member, matching);
		
		return "userpage";
	}
}
