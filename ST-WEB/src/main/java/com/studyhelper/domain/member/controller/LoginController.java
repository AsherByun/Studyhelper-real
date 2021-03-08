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

@Controller
@RequiredArgsConstructor
public class LoginController {
	private final MemberService memberService;

	// 로그인화면 출력
	@GetMapping("/login")
	public void login() {
	}

	// 로그아웃 버튼 클릭시 처리 컨트롤러
	@GetMapping("/logout")
	public void logout() {
	}

	@GetMapping("/userpage")
	public String userpage(Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		List<Team> teams = memberService.findMemberTeamsById(member);

		memberService.changeRole(member);

		model.addAttribute("teams", teams);
		return "userpage";
	}
}
