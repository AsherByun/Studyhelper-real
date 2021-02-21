package com.studyhelper.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.domain.entity.Member;
import com.domain.enums.Role;
import com.studyhelper.domain.member.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequiredArgsConstructor
public class SignUpController {
	public final MemberService memberService;

	
	@GetMapping("/signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("/signup")
	public String makeId(Member member) {
		member.setRole(Role.ROLE_MEMBER);

		if (memberService.checkIdAndNickName(member)) {
			return "signup";
		}
		memberService.saveMember(member);

		return "redirect:login";
	}
}