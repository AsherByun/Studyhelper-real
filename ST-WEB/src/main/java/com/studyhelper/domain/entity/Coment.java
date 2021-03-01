package com.studyhelper.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Coment {
	@Id @GeneratedValue
	private Long seq;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_SEQ")
	private Board board;
	
	private String content;
	private String id;
}
