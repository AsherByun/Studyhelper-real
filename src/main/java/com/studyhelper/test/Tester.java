package com.studyhelper.test;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Tester {
	@Id
	@GeneratedValue
	private long seq;
	private String name;
}
