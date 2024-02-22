package site.greenwave.point.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import site.greenwave.point.entity.PointEntity;

public interface PointRepository extends JpaRepository<PointEntity, Integer>, QuerydslPredicateExecutor<PointEntity>{
	
	//포인트 내역: 현재 보유 포인트
	@Query("SELECT " +
	           "(SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	           " WHERE pe.memberEntity.memberNo = :memberNo AND pe.changeValue = 0) " +
	           "- " +
	           "(SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	           " WHERE pe.memberEntity.memberNo = :memberNo AND pe.changeValue = 1)")
	Integer getCurrentPoint(Integer memberNo);
	
	//포인트 내역: 총 충전 포인트
	@Query("SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	       "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.changeValue = 0 " +
	           "AND pe.changeCause = 0")
	Integer getTotalChargePoints(Integer memberNo);
	
	//포인트 내역: 이번달 충전 포인트
	@Query("SELECT SUM(pe.pointValue) FROM PointEntity pe " +
		       "WHERE pe.memberEntity.memberNo = :memberNo " +
		           "AND pe.changeValue = 0 " +
		           "AND pe.changeCause = 0 " +
		           "AND YEAR(pe.pointDate) = :year " +
		           "AND MONTH(pe.pointDate) = :month")
	Integer getMonthlyChargePoints(Integer memberNo, Integer year, Integer month);
	
	
	//멤버 넘버, 증감 구분, 작물번호, 날짜에 따른 전체 리스트
	List<Object[]> findPointsByMemberNoAndChangeValueAndPointDate(Integer memberNo, Integer changeValue, Integer year, Integer month);
	
	
	//작물 영수증: 해당 작물에 소비한 총 금액
	@Query("SELECT SUM(pe.pointValue) FROM PointEntity pe " +
	           "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.cropEntity.cropNo = :cropNo " +
	           "AND pe.changeValue = 1")
	Integer getTotalAmountSpentOnCrop(Integer memberNo, Integer cropNo);
	
	//작물 영수증: 해당 작물에 소비한 갯수, 금액 - 땅
	@Query("SELECT COUNT(pe.pointNo), SUM(pe.pointValue) FROM PointEntity pe " +
	           "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.cropEntity.cropNo = :cropNo " +
	           "AND pe.changeValue = 1 " +
	           "AND pe.changeCause = 3")
	Object[] getLandReceiptInfo(Integer memberNo, Integer cropNo);
	
	//작물 영수증: 해당 작물에 소비한 갯수, 금액 - 영양제
	@Query("SELECT SUM(pe.pointValue)/5000, SUM(pe.pointValue) FROM PointEntity pe " +
	           "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.cropEntity.cropNo = :cropNo " +
	           "AND pe.changeValue = 1 " +
	           "AND pe.changeCause = 4")
	Object[] getFertilizerReceiptInfo(Integer memberNo, Integer cropNo);
	
	//작물 영수증: 해당 작물로 수령받은 포인트 - cropState 4일때 걸어야할지,,?
	@Query("SELECT pe.pointValue FROM PointEntity pe " +
	           "WHERE pe.memberEntity.memberNo = :memberNo " +
	           "AND pe.cropEntity.cropNo = :cropNo " +
	           "AND pe.changeValue = 0 " +
	           "AND pe.changeCause = 2")
	Integer getPointValueForCropCharge(Integer memberNo, Integer cropNo);
}
