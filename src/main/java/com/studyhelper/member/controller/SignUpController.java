package com.studyhelper.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.studyhelper.member.dto.Member;
import com.studyhelper.member.service.MemberService;
import com.studyhelper.properties.Role;

@Controller
public class SignUpController {
	
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
			return "/signup";
		}
		memberService.saveMember(member);
		
		return "/login";
	}
}
