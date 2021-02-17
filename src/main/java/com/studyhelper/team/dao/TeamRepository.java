package com.studyhelper.team.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.entity.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {

}
