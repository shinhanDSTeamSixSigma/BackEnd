package site.greenwave.point;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.point.entity.PointEntity;
import site.greenwave.point.repository.PointRepository;

@SpringBootTest
@Slf4j
@Log4j2
public class PointRepositoryTest {
	@Autowired
	private PointRepository pointRepo;
	
	@Test
	void read() {
		Integer member_no = 1;
		Integer changeValue = 1;

		// 년 월 설정
		YearMonth targetYearMonth = YearMonth.of(2024, 2); // 예시로 2022년 2월을 대상으로 설정

		// 해당 년 월의 시작일과 종료일 계산
		LocalDateTime startDateTime = targetYearMonth.atDay(1).atStartOfDay();
		LocalDateTime endDateTime = targetYearMonth.atEndOfMonth().atTime(23, 59, 59);

		// Timestamp로 변환
		Timestamp startTimestamp = Timestamp.valueOf(startDateTime);
		Timestamp endTimestamp = Timestamp.valueOf(endDateTime);
		
		//result, result2 결과값 같음
		Optional<PointEntity> result = pointRepo.findById(member_no);
		PointEntity result2 = pointRepo.findById(member_no).get();
		
		result.ifPresent(pointEntity -> log.info("result: "+String.valueOf(pointEntity.getPointNo())));
		log.info(String.valueOf("result2: "+result2.getPointNo()));
		
		/*
		//그냥 찾기
		List<PointEntity> entity = pointRepo.findAll();
		//member_no에 따른 전체 리스트
		List<PointEntity> entity2 = pointRepo.findAllByMemberEntityMemberNo(member_no);
		//member_no, change_value에 따른 리스트
		List<PointEntity> entity3 = pointRepo.findAllByMemberEntityMemberNoAndChangeValue(member_no, changeValue);
		//member_no, change_value, point_date에 따른 리스트
		List<PointEntity> entity4 = pointRepo.findAllByMemberEntityMemberNoAndChangeValueAndPointDateBetween(member_no, changeValue, startTimestamp, endTimestamp);
		
		entity.forEach(x -> log.info("entity: "+String.valueOf(x.getPointNo())));
	    entity2.forEach(x -> log.info("entity2: "+String.valueOf(x.getPointNo())));
	    entity3.forEach(x -> log.info("entity3: "+String.valueOf(x.getPointNo())));
	    entity4.forEach(x -> log.info("entity4: "+String.valueOf(x.getPointNo())));*/
	}
	
	@Test
	void chargePoint() {
		Integer memberNo = 1;
		Integer year = 2024;
		Integer month = 02;
		
		//현재 보유 포인트
		Integer result3 = pointRepo.getCurrentPoint(memberNo);
		log.info(String.valueOf("result3: "+result3));
		
		//총 충전 포인트
		Integer result = pointRepo.getTotalChargePoints(memberNo);
		log.info(String.valueOf("result: "+result));
		
		//이번달 충전 포인트
		Integer result2 = pointRepo.getMonthlyChargePoints(memberNo,year,month);
		log.info(String.valueOf("result2: "+result2));
	}
}
