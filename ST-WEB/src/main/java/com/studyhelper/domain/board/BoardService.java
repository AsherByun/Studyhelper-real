package com.studyhelper.domain.board;

import java.util.List;

import com.studyhelper.domain.entity.Board;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;

public interface BoardService {
	Board saveBoard(Board board,Member member,Team team);
	List<Board> getBoardsByTeam(Team team);
}
