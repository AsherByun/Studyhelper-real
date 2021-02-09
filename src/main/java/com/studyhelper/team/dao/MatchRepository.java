package com.studyhelper.team.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.entity.Matching;

@Repository
public interface MatchRepository extends CrudRepository<Matching, Long> {

}
