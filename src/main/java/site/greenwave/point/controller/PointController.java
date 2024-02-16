package site.greenwave.point.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.point.repository.PointRepository;

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
	public Integer getCurrentPoint(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.getCurrentPoint(memberNo);
		
		return result;
	}	
	//총 충전 포인트
	@GetMapping("/total-charge")
	public Integer getTotalChargePoints(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.getTotalChargePoints(memberNo);
		
		return result;
	}
	//이번달 충전 포인트
	@GetMapping("/month-charge")
	public Integer getMonthlyChargePoints(
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
	@GetMapping("/crop-charge")
	public Object[] getCropCharge(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo) {
	
		//해당 작물에 소비한 총 금액
		Integer totalAmountSpent = pointRepo.getTotalAmountSpentOnCrop(memberNo, cropNo);
		
		//해당 작물에 소비한 갯수, 금액(땅, 비료)
		Object[] landReceiptInfo = pointRepo.getLandReceiptInfo(memberNo, cropNo);
		Object[] fertilizerReceiptInfo = pointRepo.getFertilizerReceiptInfo(memberNo, cropNo);
		
	    // 여러 정보를 필요에 따라 하나의 배열로 결합
	    List<Object> combinedResult = new ArrayList<>();
	    combinedResult.add(totalAmountSpent);
	    combinedResult.addAll(Arrays.asList(landReceiptInfo));
	    combinedResult.addAll(Arrays.asList(fertilizerReceiptInfo));
		
		return combinedResult.toArray();
	}
	//해당 작물로 수령받은 포인트
	@GetMapping("/crop-point")
	public Integer getPointValueForCropCharge(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo) {
	
		Integer result = pointRepo.getPointValueForCropCharge(memberNo, cropNo);
		
		return result;
	}
}
