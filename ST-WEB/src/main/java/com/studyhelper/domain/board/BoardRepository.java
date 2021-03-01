package com.studyhelper.domain.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	@Query("select b from Board b where b.team_seq = ?1")
	List<Board> getBoardsByTeamSeq(Long teamSeq);
}
