package com.studyhelper.web.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.enums.Region;
import com.studyhelper.domain.enums.Subject;
import com.studyhelper.domain.matching.MatchingRepository;
import com.studyhelper.domain.member.MemberRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestRestController {
	private final MatchingRepository matchingRepository;
	private final MemberRepository memberRepository;

	@GetMapping("/matching/all")
	public List<Matching> getMatchingAll() {
		return matchingRepository.findAll();
	}
	
	@GetMapping("/matching/make")
	public void makeMatchingTest() {
		
		for(int i=0;i<100;i++) {
			Member member = memberRepository.findById("king"+i).get();
			Matching matching = new Matching();
			matching.setRegion(Region.values()[i%4]);
			matching.setSubject(Subject.values()[i%4]);
			matching.setSize(4);
			matching.setMemberId(member.getId());
			
			matchingRepository.save(matching);
		}
	}
}
