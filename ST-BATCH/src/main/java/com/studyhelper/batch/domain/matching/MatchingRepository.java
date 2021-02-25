package com.studyhelper.batch.domain.matching;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.entity.Matching;


@Repository
public interface MatchingRepository extends CrudRepository<Matching, String>{
	@Override
	List<Matching> findAll();
}
