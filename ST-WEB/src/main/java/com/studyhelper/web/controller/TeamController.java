package com.studyhelper.web.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.chat.pubsub.RedisPublisher;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.member.MemberService;
import com.studyhelper.domain.team.TeamRepository;
import com.studyhelper.domain.team.TeamService;
import com.studyhelper.member.security.SecurityUser;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class TeamController {
	private final RedisPublisher redisPublisher;
	private final ChatRoomRepository chatRoomRepository;
	private final MemberService memberSerivce;
	private final TeamService teamService;
	private final TeamRepository teamRepository;

	@GetMapping("/team/chatting")
	public String teamChatting(Team team, Model model) {
		team = teamRepository.findById(team.getSeq()).get();

		if (team.getChatRoomId() == null) {
			ChatRoom chatRoom = chatRoomRepository.createChatRoom(team.getSeq().toString());
			team = teamService.saveTeamChatId(team, chatRoom);
		}
		return "redirect:/chat/room/enter/"+team.getChatRoomId();
	}

	@GetMapping("/chat/info")
	@ResponseBody
	public String getUserInfos(@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();
		
		return member.getId();
	}

	@GetMapping("/team/main")
	public String teamMain(Team team, Model model) {
		List<Member> teamMembers = teamService.findMembersSameTeams(team);
		Team targetTeam = teamRepository.findById(team.getSeq()).get();

		model.addAttribute("team", targetTeam);
		model.addAttribute("members", teamMembers);
		return "teamMainPage";
	}
}
