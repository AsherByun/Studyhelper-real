package com.studyhelper.domain.matching.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.studyhelper.domain.matching.entity.enums.Region;
import com.studyhelper.domain.matching.entity.enums.Subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@RedisHash("matchings")
public class Matching {
	@Id
	private String seq;
	@Indexed
	private int size;
	@Indexed
	private Region region;
	@Indexed
	private Subject subject;
	@Indexed
	private String memberId;
	private String requestMatchingDate;
}
