package com.studyhelper.domain.board.service;

import java.util.List;

import com.studyhelper.domain.board.entity.Board;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.team.entity.Team;

public interface BoardService {
	Board saveBoard(Board board,Member member,Team team);
	List<Board> getBoardsByTeam(Team team);
	Board getBoardWithTeam(long seq);
}
