package com.domain.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.domain.enums.Region;
import com.domain.enums.Subject;

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
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date requestMatchingDate;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	private Member member;

	public void setMember(Member member) {
		this.member = member;
		member.getMatchs().add(this);
	}
	
}
