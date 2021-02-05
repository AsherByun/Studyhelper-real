package com.studyhelper.member.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.studyhelper.entity.Member;

public interface MemberRepository extends CrudRepository<Member, String> {
	@Query(value = "select exists ( select id, nick_name from member where nick_name = ?1 or id = ?2 ) as isSame", nativeQuery = true)
	int isSameIdOrNickName(String nickName,String id);	//key값과 unique를 정해놔서 없어도 될거같음 -> 처리방식 찾기
}
