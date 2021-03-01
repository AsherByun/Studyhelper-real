package com.studyhelper.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyhelper.domain.board.BoardService;
import com.studyhelper.domain.entity.Board;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.team.TeamRepository;
import com.studyhelper.member.security.SecurityUser;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;
	
	@GetMapping("/board/boardLists")
	public String getBoardLists(Team team,Model model) {
		
		
		return "board/getBoardList";
	}
	
	@GetMapping("/board/insertBoard")
	public String insertBoardGet(Model model,Team team) {
		model.addAttribute("teamSeq",team.getSeq());
		
		return "board/insertBoard";
	}

	@PostMapping("/board/insertBoard")
	public String insertBoardPost(Team team,Board board,@AuthenticationPrincipal SecurityUser securityUser) {
		boardService.saveBoard(board, securityUser.getMember(), team);

		return "login";
	}
}
