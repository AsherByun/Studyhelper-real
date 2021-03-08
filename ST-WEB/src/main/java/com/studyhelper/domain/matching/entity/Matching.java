package com.studyhelper.domain.matching.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.studyhelper.domain.matching.entity.enums.Region;
import com.studyhelper.domain.matching.entity.enums.Subject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@RedisHash("matchings")
public class Matching {
	@Id
	private String seq;
	private int size;
	private Region region;
	private Subject subject;
	private String memberId;
	private String requestMatchingDate;
}
