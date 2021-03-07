package com.studyhelper.domain.board;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.studyhelper.domain.entity.Board;
import com.studyhelper.domain.entity.Member;
import com.studyhelper.domain.entity.Team;
import com.studyhelper.domain.member.MemberRepository;
import com.studyhelper.domain.team.TeamRepository;

import lombok.RequiredArgsConstructor;

@Service
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

		return board;
	}

	@Transactional
	@Override
	public List<Board> getBoardsByTeam(Team team) {
		team = teamRepository.findTeamByFetchBoard(team.getSeq());
		
		
		return team.getBoards();
	}
	
	@Transactional
	@Override
	public Board getBoardWithTeam(long seq) {

		return boardRepository.findBoardByIdFetchTeam(seq);
	}

}
