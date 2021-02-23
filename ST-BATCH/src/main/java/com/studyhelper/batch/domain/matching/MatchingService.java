package com.studyhelper.batch.domain.matching;

import org.springframework.stereotype.Repository;

import com.studyhelper.domain.entity.Matching;

public interface MatchingService {
	void insertMatching(Matching matching);
}
