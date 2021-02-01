package com.studyhelper.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	@GetMapping("/hello")
	public String helloTest() {
		return "안녕하엥오오요요옹";
	}
}
