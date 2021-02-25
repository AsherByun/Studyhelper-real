package com.studyhelper.web.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.member.MemberService;
import com.studyhelper.domain.team.TeamService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class TeamController {
	private final MemberService memberSerivce;
	private final TeamService teamService;
	
	@GetMapping("/team/main")
	public String teamMain(Team team,Model model) {
		List<Member> teamMembers = teamService.findMembersSameTeams(team);
		
		model.addAttribute("members", teamMembers);
		return "teamMainPage";
	}
}
