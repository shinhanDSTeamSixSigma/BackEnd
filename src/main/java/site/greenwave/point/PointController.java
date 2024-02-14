package site.greenwave.point;

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
	
	@GetMapping("/point-detail")
	public List<Object[]> getPointList(
            @RequestParam("memberNo") Integer memberNo,
            @RequestParam("cropNo") Integer cropNo,
            @RequestParam("changeValue") Integer changeValue,
            @RequestParam("year") Integer year,
            @RequestParam("month") Integer month) {
		
		List<Object[]> entity = pointRepo.findPointsByMemberNoAndChangeValueAndPointDate(
                memberNo, cropNo, changeValue, year, month);
	    return entity;
	}
}
