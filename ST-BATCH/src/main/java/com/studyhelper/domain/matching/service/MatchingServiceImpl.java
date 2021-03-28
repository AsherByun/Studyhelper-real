package com.studyhelper.domain.matching.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.matching.entity.Matching;
import com.studyhelper.domain.matching.repo.MatchingRepository;
import com.studyhelper.domain.team.entity.Team;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatchingServiceImpl implements MatchingService{
	private final MatchingRepository matchRepository;
	private final ChatRoomRepository chatRoomRepository;
	
	@Transactional
	@Override
	public void insertMatching(Matching matching) {
		matchRepository.save(matching);
	}

	@Transactional
	@Override
	public void mappingChatRoom(Team team) {
		ChatRoom chatRoom = chatRoomRepository.createChatRoom(team.getTeamName());
		team.setChatRoomId(chatRoom.getRoomId());
	}

}
