package com.studyhelper.domain.matching.service;

import org.springframework.stereotype.Repository;

import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.team.entity.Team;

public interface MatchingService {
	void insertMatching(Matching matching);
	void mappingChatRoom(Team team);
}
