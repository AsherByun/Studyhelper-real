package com.studyhelper.domain.team.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
import com.studyhelper.domain.chat.pubsub.RedisPublisher;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.member.service.MemberService;
import com.studyhelper.domain.team.entity.Team;
import com.studyhelper.domain.team.repo.TeamRepository;
import com.studyhelper.domain.team.service.TeamService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class TeamController {
	private final ChatRoomRepository chatRoomRepository;
	private final MemberService memberSerivce;
	private final TeamService teamService;
	private final TeamRepository teamRepository;

	// 팀 채팅방으로 이동
	@GetMapping("/team/chatting")
	public String teamChatting(Team team, Model model) {
		team = teamRepository.findById(team.getSeq()).get();

		if (team.getChatRoomId() == null) {
			ChatRoom chatRoom = chatRoomRepository.createChatRoom(team.getSeq().toString());
			team = teamService.saveTeamChatId(team, chatRoom);
		}
		return "redirect:/chat/room/enter/" + team.getChatRoomId();
	}

	// 채팅방 입장시 정보를 제공
	@GetMapping("/chat/info")
	@ResponseBody
	public String getUserInfos(@AuthenticationPrincipal SecurityUser securityUser) {
		Member member = securityUser.getMember();

		return member.getId();
	}

	// 팀메인페이지로 이동
	@GetMapping("/team")
	public String teamMain(Team team, Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		List<Member> teamMembers = teamService.findMembersSameTeams(team);
		Team targetTeam = teamService.findTeam(team);
		Member member = securityUser.getMember();

		if (!memberSerivce.isInThisTeam(team, member)) {
			return "userpage";
		}

		model.addAttribute("team", targetTeam);
		model.addAttribute("members", teamMembers);
		return "teamMainPage";
	}
}
