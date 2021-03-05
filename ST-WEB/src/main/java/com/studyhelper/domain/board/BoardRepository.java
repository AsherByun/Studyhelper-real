package com.studyhelper.domain.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.entity.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
	@Query(value = "select b from Board b join fetch b.team where b.seq= :seq")
	Board findBoardByIdFetchTeam(@Param("seq") long seq);
}
