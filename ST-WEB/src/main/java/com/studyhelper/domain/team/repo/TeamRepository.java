package com.studyhelper.domain.team.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.board.entity.Board;
import com.studyhelper.domain.team.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
	@Query(value = "SELECT t FROM Team t JOIN FETCH t.boards WHERE t.seq = :seq")
	Team findTeamByFetchBoard(@Param("seq") Long seq);
}
