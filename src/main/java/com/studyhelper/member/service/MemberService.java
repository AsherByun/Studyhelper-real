package com.studyhelper.member.service;

import com.studyhelper.member.dto.Member;

public interface MemberService {
	boolean checkIdAndNickName(Member member);
	Member saveMember(Member member);
}
