package com.studyhelper.domain.matching.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.matching.entity.Matching;

@Repository
public interface MatchingRepository extends CrudRepository<Matching, String> {
	@Override
	List<Matching> findAll();
}
