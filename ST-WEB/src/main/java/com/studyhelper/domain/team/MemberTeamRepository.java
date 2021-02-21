package com.studyhelper.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.entity.MemberTeam;

@Repository
public interface MemberTeamRepository extends JpaRepository<MemberTeam, Long> {

}
