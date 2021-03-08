package com.studyhelper.domain.comment.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.studyhelper.domain.board.entity.Board;

import antlr.collections.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
	@Id @GeneratedValue
	private Long seq;
	
	@ManyToOne
	@JoinColumn(name = "BOARD_SEQ")
	private Board board;
	
	private String content;
	private String id;
}
