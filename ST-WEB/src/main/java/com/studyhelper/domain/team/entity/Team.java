package com.studyhelper.domain.team.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import com.studyhelper.domain.board.entity.Board;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Team")
@Getter
@Setter
@OptimisticLocking(type = OptimisticLockType.VERSION)
public class Team {
	@Id
	@GeneratedValue
	private Long seq;
	
	//팀 이름을 동시에 바꿀수 있기 때문에 낙관적 락사용
	@Version
	private Long version;
	
	private String teamName;
	private String chatRoomId;

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private Set<MemberTeam> memberTeams = new HashSet<MemberTeam>();

	@OneToMany(mappedBy = "team", fetch = FetchType.LAZY)
	private List<Board> boards = new ArrayList();
	
	public void addMemberTeams(MemberTeam memberTeam) {
		memberTeams.add(memberTeam);
	}

}
