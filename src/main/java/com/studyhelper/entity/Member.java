package com.studyhelper.entity;

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

import com.studyhelper.enums.Gender;
import com.studyhelper.enums.Role;

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

	@OneToMany(mappedBy = "member")
	private Set<MemberTeam> memberTeams = new HashSet<MemberTeam>();

	@OneToMany(mappedBy = "member")
	private Set<Matching> matchs = new HashSet<Matching>();

	public Set<Matching> getMatchs() {
		return matchs;
	}

	public void setMatchs(Set<Matching> matchs) {
		this.matchs = matchs;
	}

	public void addMemberTeams(MemberTeam memberTeam) {
		memberTeams.add(memberTeam);
	}

	public Set<MemberTeam> getMemberTeams() {
		return memberTeams;
	}

	public void setMemberTeams(Set<MemberTeam> memberTeams) {
		this.memberTeams = memberTeams;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFirstAccess() {
		return isFirstAccess;
	}

	public void setFirstAccess(boolean isFirstAccess) {
		this.isFirstAccess = isFirstAccess;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
