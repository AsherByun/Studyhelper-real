package com.studyhelper.domain.matching.controller;

import java.util.Date;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.matching.service.MatchingService;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
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
		
		return "redirect:/userpage";
	}
	
}
