package com.studyhelper.domain.team.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.studyhelper.domain.chat.dao.ChatRoomRepository;
import com.studyhelper.domain.chat.dto.ChatRoom;
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
	public String teamChatting(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
		Team team = securityUser.getTeam();
		team = teamRepository.findById(team.getSeq()).get();

		if (team.getChatRoomId() == null) {
			ChatRoom chatRoom = chatRoomRepository.createChatRoom(team.getSeq().toString());
			team = teamService.saveTeamChatId(team, chatRoom);
		}
		return "redirect:/chat/room/enter/" + team.getChatRoomId();
	}

	// 팀메인페이지로 이동
	@GetMapping("/team")
	public String teamMain(@RequestParam(value = "seq", required = false, defaultValue = "-1") long teamSeq,
			Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		
		Team team;
		
		if (teamSeq == -1 && securityUser.getTeam() == null) {
			return "userpage";
		} else if (teamSeq == -1) {
			team = securityUser.getTeam();
		}else {
			team = teamRepository.findById(teamSeq).get();
		}
		
		if (!memberSerivce.isInThisTeam(team, securityUser.getMember())) {
			return "userpage";
		}
		// 시큐리티 맴버에 현재 들어가있는 팀을 저장해준다.
		securityUser.setTeam(team);

		List<Member> teamMembers = teamService.findMembersSameTeams(team);
		model.addAttribute("team", team);
		model.addAttribute("members", teamMembers);
		return "teamMainPage";
	}
}
