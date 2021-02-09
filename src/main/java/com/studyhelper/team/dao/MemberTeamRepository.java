package com.studyhelper.team.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.entity.MemberTeam;

@Repository
public interface MemberTeamRepository extends CrudRepository<MemberTeam, Long> {

}
