package com.studyhelper.domain.chat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.chat.service.ChatService;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.team.entity.Team;

import java.util.List;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

	private final ChatRoomRepository chatRoomRepository;
	private final ChatService chatService;

	@GetMapping("/room")
	public String rooms(Model model) {
		return "/chat/room";
	}

	@GetMapping("/rooms")
	@ResponseBody
	public List<ChatRoom> room() {
		List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
		for (ChatRoom chatRoom : chatRooms) {
			chatRoom.setUserCount(chatRoomRepository.getUserCount(chatRoom.getRoomId()));
		}
		return chatRooms;
	}

	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(@RequestParam String name) {
		return chatRoomRepository.createChatRoom(name);
	}

	@GetMapping("/room/enter/{roomId}")
	public String roomDetail(Model model, @PathVariable String roomId) {
		model.addAttribute("roomId", roomId);
		return "/chat/roomdetail";
	}

	// 채팅방 입장시 정보를 제공
	@GetMapping("/info")
	@ResponseBody
	public Map<String, String> getUserInfos(@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		Team team = securityUser.getTeam();
		
		
		return chatService.getRoomInfo(member.getId(), team.getTeamName());
	}

	@GetMapping("/room/{roomId}")
	@ResponseBody
	public ChatRoom roomInfo(@PathVariable String roomId) {
		return chatRoomRepository.findRoomById(roomId);
	}
}