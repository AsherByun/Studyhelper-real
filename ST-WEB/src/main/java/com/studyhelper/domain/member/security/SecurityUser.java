package com.studyhelper.domain.member.security;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.studyhelper.domain.member.entity.Member;
import com.studyhelper.domain.team.entity.Team;

public class SecurityUser extends User {
	private static final long serialVersionUID = 1L;
	private Member member;
	private Team team;

	public SecurityUser(Member member) {
		super(member.getId(), member.getPassword(), AuthorityUtils.createAuthorityList(member.getRole().toString()));
		this.member = member;
	}

	public Member getMember() {
		return member;
	}

	public Team getTeam() {
		return team;
	}
	
	public void setTeam(Team team) {
		this.team=team;
	}
}
