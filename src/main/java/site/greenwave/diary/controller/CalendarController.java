package site.greenwave.diary.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.crop.CropRepository;
import site.greenwave.diary.entity.DiaryEntity;
import site.greenwave.diary.service.DiaryService;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
    private DiaryService diaryService;
	@Autowired
    private CropRepository cropRepo;
	
	//날짜 기준 일기 내역
	@GetMapping("/list")
	public ResponseEntity<List<DiaryEntity>> getDiaryInfoByDate(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo,
			@RequestParam("diaryDate") String diaryDate){
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	    try {
	        // String을 Date로 변환
	        Date parsedDate = dateFormat.parse(diaryDate);

	        // 변환된 Date를 사용하여 로직 수행
	        List<DiaryEntity> list = diaryService.getDiaryInfoByDate(memberNo, cropNo, parsedDate);

	        return ResponseEntity.status(HttpStatus.OK).body(list);
	    } catch (ParseException e) {
	        // 날짜 형식이 올바르지 않을 경우 처리
	        e.printStackTrace(); // 또는 로깅 등의 작업 수행
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	    }
	}
	
	//작물 구매일
	@GetMapping("/crop/buy-date")
	 ResponseEntity<Date> getCropBuyDate(
	        @RequestParam("memberNo") Integer memberNo,
	        @RequestParam("cropNo") Integer cropNo) {

	    Date cropBuyDate = cropRepo.getCropBuyDate(memberNo, cropNo);

	    if (cropBuyDate != null) {
	        return ResponseEntity.ok(cropBuyDate);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
}
