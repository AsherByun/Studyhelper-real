package com.studyhelper.domain.entity;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "BOARD")
@Entity
public class Board {
	@Id
	@GeneratedValue
	private Long seq;

	private String title;
	private String content;

	@ManyToOne
	@JoinColumn(name = "MEMBER_ID")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "TEAM_SEQ")
	private Team team;

	@OneToMany(mappedBy = "board", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Coment> coments = new ArrayList<Coment>();

}
