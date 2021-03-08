package com.studyhelper.batch.domain.matching.service;

import org.springframework.stereotype.Repository;

import com.studyhelper.batch.domain.matching.entity.Matching;

public interface MatchingService {
	void insertMatching(Matching matching);
}
