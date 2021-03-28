package com.studyhelper.domain.matching.service;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.member.entity.Member;

public interface MatchingService {
	void saveAndSendMatching(Member member,Matching matching);
}
