package site.greenwave.member;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.member.MemberEntity;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer>{

}
