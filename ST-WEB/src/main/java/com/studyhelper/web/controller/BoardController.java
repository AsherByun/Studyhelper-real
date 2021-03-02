package com.studyhelper.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyhelper.domain.board.BoardRepository;
import com.studyhelper.domain.board.BoardService;
import com.studyhelper.domain.entity.Board;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.team.TeamRepository;
import com.studyhelper.exception.board.BoardNotFoundException;
import com.studyhelper.member.security.SecurityUser;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;
	private final BoardRepository boardRepository;
	private final TeamRepository teamRepository;
	
	@GetMapping("/board/getBoard")
	public String getBoard(Board board,Model model) {
		board = boardRepository.findById(board.getSeq()).get();
		
		model.addAttribute("board", board);
		model.addAttribute("team",board.getTeam());
		
		return "board/getBoard";
	}
	
	@GetMapping("/board/boardLists")
	public String getBoardLists(Team team,Model model) {
		
		List<Board> boardList = boardService.getBoardsByTeam(team);
		model.addAttribute("boardList",boardList);
		model.addAttribute("team", team);
		
		return "board/getBoardList";
	}
	
	@GetMapping("/board/insertBoard")
	public String insertBoardGet(Model model,Team team) {
		model.addAttribute("teamSeq",team.getSeq());
		
		return "board/insertBoard";
	}

	@PostMapping("/board/insertBoard")
	public String insertBoardPost(Model model,Team team,Board board,@AuthenticationPrincipal SecurityUser securityUser) {
		if (board.getContent().equals("")||board.getTitle().equals("")) {
			throw new BoardNotFoundException("");
		}
		
		boardService.saveBoard(board, securityUser.getMember(), team);
		model.addAttribute("team", teamRepository.findById(team.getSeq()).get());
		model.addAttribute("member", securityUser.getMember());
		
		return "teamMainPage";
	}
}
