package com.studyhelper.domain.member.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.member.service.MemberService;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class UserController {
	private final MemberService memberService;
	
	//유저 메일페이지로 이동
	@GetMapping("/userpage/mail")
	public String mailpage() {
		
		return "mailpage";
	}
	
	//해당 회원이 속한 팀리스트를 보여주는 페이지
	@GetMapping("/teams")
	public String teamList(Model model,@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		List<Team> teams = memberService.findMemberTeamsById(member);
		
		model.addAttribute("teams", teams);
		return "teamList";
	}
}
