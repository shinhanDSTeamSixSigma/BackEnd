package site.greenwave.point.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.greenwave.crop.CropDto;
import site.greenwave.crop.CropService;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.repository.PointRepository;
import site.greenwave.point.service.PointService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/pay")
public class PointController {
	
	@Autowired
	private PointRepository pointRepo;
    @Autowired
    private PointService pointService;
    @Autowired
    private CropService cropService;
	
	/* 포인트 내역 */
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
	
	/* 포인트 사용 : 영양제, 포인트 적립 */
    @PostMapping("/register-point")
    public Map<String, Object> registerPoint(@RequestBody PointDto pointDto) {
    	log.info("==========="+pointDto);
        return pointService.registerPoint(pointDto);
    }
	/* 포인트 사용 : 땅 적립 */
    @PostMapping("/register-crop")
    public ResponseEntity<Integer> registerCrop(@RequestBody Map map) {
    	log.info("==========="+map.toString());
    	
    	CropDto dto = new CropDto();
    	
		Integer cropNo = cropService.registerCrop(map);
		return new ResponseEntity<>(cropNo, HttpStatus.OK);
    }
}
