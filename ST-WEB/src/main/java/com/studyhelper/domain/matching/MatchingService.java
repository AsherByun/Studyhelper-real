package com.studyhelper.domain.matching;

import com.studyhelper.domain.entity.Matching;
import com.studyhelper.domain.entity.Member;

public interface MatchingService {
	void saveMatching(Member member,Matching matching);
}
