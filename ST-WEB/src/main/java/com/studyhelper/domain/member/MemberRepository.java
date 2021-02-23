package com.studyhelper.domain.member;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.studyhelper.domain.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
	@Query(value = "select exists ( select member_id, nick_name from member where nick_name = ?1 or member_id = ?2 ) as isSame", nativeQuery = true)
	int isSameIdOrNickName(String nickName,String id);	//key값과 unique를 정해놔서 없어도 될거같음 -> 처리방식 찾기

	@Query(value = "select * from member where member_id=?1",nativeQuery = true)
	Optional<Member> findMemberById(String id);
	
	@Query(value = "select m from member m join fetch m.member_team",nativeQuery = true)
	List<Member> findMemberAll();
}
