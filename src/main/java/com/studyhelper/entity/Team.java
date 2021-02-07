package com.studyhelper.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Team")
@Getter
@Setter
@ToString
public class Team {
	@Id
	@GeneratedValue
	private Long seq;

	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER)
	private List<MemberTeam> memberTeams = new ArrayList<MemberTeam>();

	public void addMemberTeams(MemberTeam memberTeam) {
		memberTeams.add(memberTeam);
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public List<MemberTeam> getMemberTeams() {
		return memberTeams;
	}

	public void setMemberTeams(List<MemberTeam> memberTeams) {
		this.memberTeams = memberTeams;
	}

}
