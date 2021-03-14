package com.studyhelper.domain.chat.config;

import java.security.Principal;
import java.util.Optional;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatMessage;
import com.studyhelper.domain.chat.service.ChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class StompHandler implements ChannelInterceptor{
	
	private final ChatRoomRepository chatRoomRepository;
	private final ChatService chatService;
	
	@Override
	public Message<?> preSend(Message<?> message, MessageChannel channel) {
		StompHeaderAccessor accessor=StompHeaderAccessor.wrap(message);
		if (StompCommand.CONNECT == accessor.getCommand()) {
			log.info(message.toString());
		}
		else if (StompCommand.SUBSCRIBE == accessor.getCommand()) {
			String roomId = chatService
					.getRoomId(Optional.ofNullable((String)message.getHeaders().get("simpDestination")).orElse("InvalidRoomId"));
			String sessionId = (String)message.getHeaders().get("simpSessionId");
			//새로 채팅방에 접속한 유저 정보 갱신
			chatRoomRepository.setUserEnterInfo(sessionId, roomId);
			//새로들어온 채팅방 인원수 +1
			chatRoomRepository.plusUserCount(roomId);
			//입장메시지를 채팅방에 발송
			String name =Optional.ofNullable((Principal)message.getHeaders()
					.get("simpUser")).map(Principal::getName).orElse("UnknownUser");
			
			chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.ENTER).roomId(roomId).sender(name).build());
			log.info("SUBSCRIBED {}, {}.{}",name,roomId,sessionId);
		}
		else if(StompCommand.DISCONNECT == accessor.getCommand()){
			String sessionId = (String)message.getHeaders().get("simpSessionId");
			String roomId = chatRoomRepository.getUserEnterRoomId(sessionId);
			
			chatRoomRepository.minusUserCount(roomId);
			
			String name = Optional.ofNullable((Principal)message.getHeaders().get("simpUser"))
					.map(Principal::getName).orElse("UnknownUser");
			chatService.sendChatMessage(ChatMessage.builder().type(ChatMessage.MessageType.QUIT).roomId(roomId).sender(name).build());
			chatRoomRepository.removeUserEnterInfo(sessionId);
            log.info("DISCONNECTED {}, {}", sessionId, roomId);
		}
		
		return message;
	}

}
