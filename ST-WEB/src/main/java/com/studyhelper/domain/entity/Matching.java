package com.studyhelper.domain.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import com.studyhelper.domain.enums.Region;
import com.studyhelper.domain.enums.Subject;

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
