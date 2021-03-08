package com.studyhelper.domain.member.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.studyhelper.domain.board.entity.Board;
import com.studyhelper.domain.matching.entity.enums.Gender;
import com.studyhelper.domain.member.entity.enums.Role;
import com.studyhelper.domain.team.entity.MemberTeam;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class Member {
	@Id
	@Column(name = "member_id")
	private String id;
	private String name;
	private int age;

	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	private Role role;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(nullable = false, unique = true)
	private String nickName;

	private boolean enabled;

	@Column(columnDefinition = "boolean default false")
	private boolean isFirstAccess;

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private Set<MemberTeam> memberTeams = new HashSet<MemberTeam>();

	@OneToMany(mappedBy = "member", fetch = FetchType.LAZY)
	private List<Board> boards = new ArrayList();
	
	public void addMemberTeams(MemberTeam memberTeam) {
		memberTeams.add(memberTeam);
	}


}
