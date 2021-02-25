package com.studyhelper.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.matching.MatchingRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TestRestController {
	private final MatchingRepository matchingRepository;

	@GetMapping("/matching/all")
	public List<Matching> getMatchingAll() {
		return matchingRepository.findAll();
	}
}
