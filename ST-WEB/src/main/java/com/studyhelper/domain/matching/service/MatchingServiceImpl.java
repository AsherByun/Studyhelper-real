package com.studyhelper.domain.matching.service;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.member.entity.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService{
	private final MatchingRepository matchingRepository;
	
	@Override
	@Transactional
	public void saveMatching(Member member,Matching matching) {
		matching.setMemberId(member.getId());
		matching.setRequestMatchingDate(LocalDate.now().toString());
		
		matchingRepository.save(matching);
	}

}
