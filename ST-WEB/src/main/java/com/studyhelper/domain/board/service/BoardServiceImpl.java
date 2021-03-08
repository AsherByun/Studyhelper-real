package com.studyhelper.domain.board.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.domain.board.entity.Board;
import com.studyhelper.domain.board.repo.BoardRepository;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.member.repo.MemberRepository;
import com.studyhelper.domain.team.entity.Team;
import com.studyhelper.domain.team.repo.TeamRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	private final BoardRepository boardRepository;
	private final TeamRepository teamRepository;
	private final MemberRepository memberRepository;

	@Transactional
	@Override
	public Board saveBoard(Board board, Member member, Team team) {
		team = teamRepository.findById(team.getSeq()).get();
		member = memberRepository.findById(member.getId()).get();

		board.setMember(member);
		board.setTeam(team);

		team.getBoards().add(board);
		member.getBoards().add(board);

		boardRepository.save(board);
		log.info("게시판 생성 -->  회원 아이디: " + member.getId() + " 팀 SEQ: " + team.getSeq());

		return board;
	}

	@Transactional
	@Override
	public List<Board> getBoardsByTeam(Team team) {
		Team targetTeam = teamRepository.findTeamByFetchBoard(team.getSeq());

		if (targetTeam == null) {
			return null;
		}

		return targetTeam.getBoards();
	}

	@Transactional
	@Override
	public Board getBoardWithTeam(long seq) {

		return boardRepository.findBoardByIdFetchTeam(seq);
	}

}
