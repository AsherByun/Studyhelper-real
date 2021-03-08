package com.studyhelper.domain.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.studyhelper.domain.board.entity.Board;
import com.studyhelper.domain.board.exception.BoardNotFoundException;
import com.studyhelper.domain.board.repo.BoardRepository;
import com.studyhelper.domain.board.service.BoardService;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.security.SecurityUser;
import com.studyhelper.domain.team.entity.Team;
import com.studyhelper.domain.team.repo.TeamRepository;

@Controller
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;
	private final TeamRepository teamRepository;

	// 게시판 상세보기
	@GetMapping("/board")
	public String getBoard(Board board, Model model) {
		board = boardService.getBoardWithTeam(board.getSeq());

		model.addAttribute("board", board);
		model.addAttribute("team", board.getTeam());

		return "board/getBoard";
	}

	// 해당 팀 게시판 전부 가져오기
	@GetMapping("/boards")
	public String getBoardLists(Team team, Model model) {
		List<Board> boardList = boardService.getBoardsByTeam(team);

		model.addAttribute("team", team);
		model.addAttribute("boardList", boardList);

		return "board/getBoardList";
	}

	// 게시판 생성 화면
	@GetMapping("/board/format")
	public String insertBoardGet(Model model, Team team) {
		model.addAttribute("teamSeq", team.getSeq());

		return "board/insertBoard";
	}

	// 게시판 생성
	@PostMapping("/board/format")
	public String insertBoardPost(Model model, Team team, Board board,
			@AuthenticationPrincipal SecurityUser securityUser) {
		if (board.getContent().equals("") || board.getTitle().equals("")) {
			throw new BoardNotFoundException("");
		}

		boardService.saveBoard(board, securityUser.getMember(), team);
		model.addAttribute("team", teamRepository.findById(team.getSeq()).get());
		model.addAttribute("member", securityUser.getMember());

		return "teamMainPage";
	}
}
