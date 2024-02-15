package site.greenwave.point;

import java.util.List;
import java.util.Optional;

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
	
	//현재 보유 포인트
	@GetMapping("/current-point")
	public Integer subtractPointValuesByChangeValues(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.subtractPointValuesByChangeValues(memberNo);
		
		return result;
	}
	
	//총 충전 포인트
	@GetMapping("/total-charge")
	public Integer sumPointValueByConditions(
			@RequestParam("memberNo") Integer memberNo) {
	
		Integer result = pointRepo.sumPointValueByConditions(memberNo);
		
		return result;
	}
	
	//이번달 충전 포인트
	@GetMapping("/month-charge")
	public Integer sumPointValueByConditionsAndPointDate(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
	
		Integer result = pointRepo.sumPointValueByConditionsAndPointDate(memberNo, year, month);
		
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
}
