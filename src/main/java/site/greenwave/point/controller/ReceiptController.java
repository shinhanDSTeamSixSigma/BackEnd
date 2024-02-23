package site.greenwave.point.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.crop.CropRepository;
import site.greenwave.point.repository.PointRepository;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/receipt")
@Log4j2
public class ReceiptController {
	
	@Autowired
	private PointRepository pointRepo;
	@Autowired
	private CropRepository cropRepo;
	
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
	
	//작물 상태
	@GetMapping("/crop-status")
	public Integer getCropState(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo) {
	
		Integer result = cropRepo.getCropState(memberNo, cropNo);
		
		return result;
	}
}
