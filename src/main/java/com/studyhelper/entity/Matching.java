package com.studyhelper.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.studyhelper.enums.Region;
import com.studyhelper.enums.Subject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@Table(name = "MATCHING")
public class Matching {
	@Id
	@GeneratedValue
	private Long seq;
	
	private int size;
	@Enumerated(EnumType.STRING)
	private Region region;
	@Enumerated(EnumType.STRING)
	private Subject subject;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
		member.getMatchs().add(this);
	}
	
}
