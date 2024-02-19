package site.greenwave.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.greenwave.member.entity.MemberEntity;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberEntity, Integer> {
    Optional<MemberEntity> findByMemberId(String memberId);
//    boolean existsByEmail(String email);
    boolean existsByMemberId(String memeberId);
}
