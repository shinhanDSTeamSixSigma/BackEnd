package site.greenwave.point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/pay")
public class PointController {
	@Autowired
	private PointRepository pointRepo;
	
	/*------------
	 * 	포인트 내역
	 ------------*/
	//현재 보유 포인트
	@GetMapping("/current-point")
	public Integer subtractPointValuesByChangeValues(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.getCurrentPoint(memberNo);
		
		return result;
	}	
	//총 충전 포인트
	@GetMapping("/total-charge")
	public Integer sumPointValueByConditions(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.getTotalChargePoints(memberNo);
		
		return result;
	}
	//이번달 충전 포인트
	@GetMapping("/month-charge")
	public Integer sumPointValueByConditionsAndPointDate(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
	
		Integer result = pointRepo.getMonthlyChargePoints(memberNo, year, month);
		
		return result;
	}
	//포인트 내역
	@GetMapping("/point-detail")
	public List<Object[]> getPointList(
            @RequestParam("memberNo") Integer memberNo,
            @RequestParam("changeValue") Integer changeValue,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
		
		List<Object[]> entity = pointRepo.findPointsByMemberNoAndChangeValueAndPointDate(
                memberNo, changeValue, year, month);
	    return entity;
	}
	
	/*---------
	 * 	영수증
	 ---------*/
	//해당 작물에 소비한 총 금액
	@GetMapping("/crop-total-charge")
	public Integer sumByMemberAndCropAndChange(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo) {
	
		Integer result = pointRepo.getTotalAmountSpentOnCrop(memberNo, cropNo);
		
		return result;
	}
	//해당 작물에 소비한 갯수, 금액
	@GetMapping("/crop-each-charge")
	public Object[] countAndSumByMemberAndCropAndChange(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo) {
	
		Object[] landReceiptInfo = pointRepo.getLandReceiptInfo(memberNo, cropNo);
		Object[] fertilizerReceiptInfo = pointRepo.getFertilizerReceiptInfo(memberNo, cropNo);
		
	    // 여러 정보를 필요에 따라 하나의 배열로 결합
	    List<Object> combinedResult = new ArrayList<>();
	    combinedResult.addAll(Arrays.asList(landReceiptInfo));
	    combinedResult.addAll(Arrays.asList(fertilizerReceiptInfo));
		
		return combinedResult.toArray();
	}
}
