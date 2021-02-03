package com.studyhelper.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.studyhelper.member.dao.MemberRepository;
import com.studyhelper.member.dto.Member;

@Service
public class MemberServiceImpl implements MemberService {
	@Autowired
	private PasswordEncoder encoder;
	
	public final MemberRepository memberRepo;
	
	public MemberServiceImpl(MemberRepository meberRepo) {
		this.memberRepo = meberRepo;
	}
	
	@Override
	public boolean checkIdAndNickName(Member member) {
		
		return memberRepo.isSameIdOrNickName(member.getNickName(), member.getId()) == 1;
	}

	@Override
	public Member saveMember(Member member) {
		member.setPassword(encoder.encode(member.getPassword()));
		return memberRepo.save(member);
	}

}
