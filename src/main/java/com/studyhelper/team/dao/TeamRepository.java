package com.studyhelper.team.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.entity.Team;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {

}
