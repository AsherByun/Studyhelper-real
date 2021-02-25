package com.studyhelper.web.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.member.MemberService;
import com.studyhelper.member.security.SecurityUser;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {
	private final MemberService memberService;
	
	@GetMapping("/userpage/mail")
	public String mailpage() {
		
		return "mailpage";
	}
	@GetMapping("/team/teamList")
	public String teamList(Model model,@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		List<Team> teams = memberService.findMemberTeamsById(member);
		
		model.addAttribute("teams", teams);
		return "teamList";
	}
}
