package site.greenwave.point;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.point.repository.PointRepository;

@SpringBootTest
@Slf4j
@Log4j2
public class ReceiptRepositoryTest {
	@Autowired
	private PointRepository pointRepo;
	
	@Test
	void receiptPoint() {
		Integer memberNo = 1;
		Integer cropNo = 1;
		
		//해당 작물에 소비한 총 금액
		Integer result = pointRepo.getTotalAmountSpentOnCrop(memberNo, cropNo);
		log.info(String.valueOf("result: "+result));
		
		//해당 작물에 소비한 갯수, 금액
		Object[] result2 = pointRepo.getLandReceiptInfo(memberNo,cropNo);
		log.info(String.valueOf("result2: "+result2.toString()));
		
		//해당 작물로 수령받은 포인트
		Integer result3 = pointRepo.getPointValueForCropCharge(memberNo,cropNo);
		log.info(String.valueOf("result3: "+result3));
	}
}
