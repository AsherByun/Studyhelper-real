package com.studyhelper.member.service;

import com.studyhelper.entity.Member;

public interface MemberService {
	boolean checkIdAndNickName(Member member);
	Member saveMember(Member member);
}
