package com.studyhelper.domain.team.controller;

import java.util.List;

import javax.persistence.OptimisticLockException;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	private final int NO_GET_TEAM_NOW = -1;

	// 팀 채팅방으로 이동
	@GetMapping("/team/chatting")
	public String teamChatting(@AuthenticationPrincipal SecurityUser securityUser, Model model) {
		Team team = securityUser.getTeam();
		String chatRoomId = teamService.findTeamChattingRoomId(team);
		
		return "redirect:/chat/room/enter/" + chatRoomId;
	}

	// 팀메인페이지로 이동
	@GetMapping("/team")
	public String teamMain(@RequestParam(value = "seq", required = false, defaultValue = "-1") long teamSeq,
			Model model, @AuthenticationPrincipal SecurityUser securityUser) {
		
		Team team;
		//팀이 선택된적도, 현재 로그인한 회원이 팀을 갖지않는다면 유저페이지로
		if (teamSeq == NO_GET_TEAM_NOW && securityUser.getTeam() == null) {
			return "userpage";
		} 
		//원하는 팀이 없다면 최근에 선택된 팀을 가져온다
		else if (teamSeq == NO_GET_TEAM_NOW) {
			team = securityUser.getTeam();
		}
		//원하는 팀이 있다면 그 팀을 가져와서 로그인 상태에 넣어준다.
		else {
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
	@GetMapping("/team/info")
	public String moveTeamInfo(@AuthenticationPrincipal SecurityUser securityUser,Model model) {
		model.addAttribute("teamName", securityUser.getTeam().getTeamName());
		
		return "teamInfoChange";
	}
	
	@PostMapping("/team/info")
	public String changeTeamName(@AuthenticationPrincipal SecurityUser securityUser,String changeTeamName) {
		try {
			teamService.changeTeamName(securityUser.getTeam(), changeTeamName);
		} catch (OptimisticLockException e) {
			log.info("팀 이름 교체 중 충돌 발생!!");
		}

		return "redirect:/team";
	}
}
