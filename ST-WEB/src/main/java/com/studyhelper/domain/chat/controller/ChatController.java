package com.studyhelper.domain.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatMessage;
import com.studyhelper.domain.chat.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class ChatController {
	
	private final ChatRoomRepository chatRoomRepository;
	private final ChatService chatService;
	
	@MessageMapping("/chat/message")
	public void message(ChatMessage message) {
		message.setUserCount(chatRoomRepository.getUserCount(message.getRoomId()));
		chatService.sendChatMessage(message);
	}
}