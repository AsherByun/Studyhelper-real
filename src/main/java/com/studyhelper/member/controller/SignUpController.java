package com.studyhelper.member.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.studyhelper.entity.Member;
import com.studyhelper.enums.Role;
import com.studyhelper.member.service.MemberService;

@Controller
public class SignUpController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	public final MemberService memberService;

	public SignUpController(MemberService memberService) {
		this.memberService = memberService;
	}

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
