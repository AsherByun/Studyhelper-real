package com.studyhelper.batch.domain.matching;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.domain.entity.Matching;

@Repository
public interface MatchingRepository extends JpaRepository<Matching, Long> {
	@Query("select m from Matching m")
	List<Matching> searchAllMatchings();
}
