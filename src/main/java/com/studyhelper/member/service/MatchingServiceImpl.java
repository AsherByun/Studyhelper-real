package com.studyhelper.member.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studyhelper.entity.Matching;
import com.studyhelper.team.dao.MatchingRepository;

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
