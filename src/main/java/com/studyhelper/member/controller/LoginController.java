package com.studyhelper.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	//로그인화면 출력
	@GetMapping("/login")
	public void login() {}
	//로그아웃 버튼 클릭시 처리 컨트롤러
	@GetMapping("/logout")
	public void logout() {}
}
