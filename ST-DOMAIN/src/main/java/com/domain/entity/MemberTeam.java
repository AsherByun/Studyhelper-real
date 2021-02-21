package com.domain.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "MEMBER_TEAM")
public class MemberTeam {
	@Id
	@GeneratedValue
	private Long seq;

	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	@ManyToOne
	@JoinColumn(name = "team_id")
	private Team team;


	public void setMember(Member member) {
		this.member = member;
		member.addMemberTeams(this);
	}

	public void setTeam(Team team) {
		this.team = team;
		team.addMemberTeams(this);
	}

}
