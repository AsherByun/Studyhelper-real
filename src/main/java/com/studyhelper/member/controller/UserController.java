package com.studyhelper.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
	
	@GetMapping("/userpage/mail")
	public String mailpage() {
		
		return "mailpage";
	}
}
