package com.studyhelper.batch.domain.matching;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studyhelper.domain.entity.Matching;

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
