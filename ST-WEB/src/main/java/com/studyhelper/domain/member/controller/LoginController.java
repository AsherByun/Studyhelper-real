package com.studyhelper.domain.member.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.repo.MemberRepository;
import com.studyhelper.domain.member.security.MemberDetailService;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.member.security.jwt.JwtTokenProvider;
import com.studyhelper.domain.member.service.MemberService;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
	private final MemberService memberService;
	private final MemberDetailService memberDetailService;
	private final JwtTokenProvider jwtTokenProvider;
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;

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
		log.info("회원 로그인 --> 회원 아이디: " + member.getId());

		memberService.changeRole(member);

		model.addAttribute("teams", teams);
		return "userpage";
	}
}
