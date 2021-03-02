package com.studyhelper.web.controller;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.matching.MatchingRepository;
import com.studyhelper.domain.matching.MatchingService;
import com.studyhelper.domain.member.MemberService;
import com.studyhelper.member.security.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class TeamMatcingController {
	private final MatchingService matchingService;

	@GetMapping("/matching/request")
	public String getMatching() {
		return "matchForm";
	}

	@PostMapping("/matching/request")
	public String postMatching(@AuthenticationPrincipal SecurityUser securityUser, Matching matching) {
		Member member = securityUser.getMember();
		matchingService.saveMatching(member, matching);
		
		return "userpage";
	}
	
}
