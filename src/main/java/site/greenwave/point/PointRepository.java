package site.greenwave.point;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PointRepository extends JpaRepository<PointEntity, Long>, QuerydslPredicateExecutor<PointEntity>{
//	//멤버 넘버에 따른 전체 리스트
//	List<PointEntity> findAllByMemberEntityMemberNo(Integer memberNo);
//	//멤버 넘버, 증감 구분에 따른 전체 리스트
//	List<PointEntity> findAllByMemberEntityMemberNoAndChangeValue(Integer memberNo, Integer changeValue);
	
	//현재 보유 포인트
	@Query("SELECT " +
	           "(SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	           " WHERE pe.memberEntity.memberNo = :memberNo AND pe.changeValue = 0) " +
	           "- " +
	           "(SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	           " WHERE pe.memberEntity.memberNo = :memberNo AND pe.changeValue = 1)")
	Integer subtractPointValuesByChangeValues(Integer memberNo);
	
	//총 충전 포인트
	@Query("SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	       "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.changeValue = 0 " +
	           "AND pe.changeCause = 0")
	Integer sumPointValueByConditions(Integer memberNo);
	
	//이번달 충전 포인트
	@Query("SELECT SUM(pe.pointValue) FROM PointEntity pe " +
		       "WHERE pe.memberEntity.memberNo = :memberNo " +
		           "AND pe.changeValue = 0 " +
		           "AND pe.changeCause = 0 " +
		           "AND YEAR(pe.pointDate) = :year " +
		           "AND MONTH(pe.pointDate) = :month")
	Integer sumPointValueByConditionsAndPointDate(Integer memberNo, Integer year, Integer month);
	
	//멤버 넘버, 증감 구분, 작물번호, 날짜에 따른 전체 리스트
	List<Object[]> findPointsByMemberNoAndChangeValueAndPointDate(Integer memberNo, Integer changeValue, Integer year, Integer month);
    
}
