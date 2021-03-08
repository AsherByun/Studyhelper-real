package com.studyhelper.batch.domain.matching.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studyhelper.batch.domain.matching.entity.Matching;
import com.studyhelper.batch.domain.matching.repo.MatchingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService{
	public final MatchingRepository matchRepository;
	
	@Transactional
	@Override
	public void insertMatching(Matching matching) {
		matchRepository.save(matching);
	}

}
