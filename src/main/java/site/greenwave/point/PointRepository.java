package site.greenwave.point;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PointRepository extends JpaRepository<PointEntity, Long>, QuerydslPredicateExecutor<PointEntity>{
//	//멤버 넘버에 따른 전체 리스트
//	List<PointEntity> findAllByMemberEntityMemberNo(Integer memberNo);
//	//멤버 넘버, 증감 구분에 따른 전체 리스트
//	List<PointEntity> findAllByMemberEntityMemberNoAndChangeValue(Integer memberNo, Integer changeValue);
	
	//멤버 넘버, 증감 구분, 작물번호, 날짜에 따른 전체 리스트
	List<Object[]> findPointsByMemberNoAndChangeValueAndPointDate(Integer memberNo, Integer cropNo, Integer changeValue, Integer year, Integer month);
    
}
