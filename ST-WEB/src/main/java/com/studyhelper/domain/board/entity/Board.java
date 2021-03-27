package com.studyhelper.domain.board.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.studyhelper.domain.comment.entity.Comment;
import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.team.entity.Team;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "BOARD")
@Entity
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Board {
	@Id
	@GeneratedValue
	private Long seq;

	private String title;
	private String content;
	
	//낙관적인 lock 사용
	@Version
	private Long version;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "TEAM_SEQ")
	private Team team;

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comment> coments = new ArrayList<Comment>();

}
