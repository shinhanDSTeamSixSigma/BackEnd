package site.greenwave.diary.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
	
	//일기 리스트
	@GetMapping("/total-list")
	public ResponseEntity<List<DiaryEntity>> getDiaryInfo(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo){
		
		List<DiaryEntity> list = diaryService.getDiaryInfo(memberNo, cropNo);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//날짜 기준 일기 내역
	@GetMapping("/list")
	public ResponseEntity<List<DiaryEntity>> getDiaryInfoByDate(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo,
			@RequestParam("diaryDate") String diaryDate){

	    // 변환된 Date를 사용하여 로직 수행
		List<DiaryEntity> list = diaryService.getDiaryInfoByDate(memberNo, cropNo, diaryDate);

		return ResponseEntity.status(HttpStatus.OK).body(list);
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
	
	//작물 구매일 및 이름
	@GetMapping("/crop/crop-info")
    ResponseEntity<Object[]> getCropDetails(
	        @RequestParam("memberNo") Integer memberNo,
	        @RequestParam("cropNo") Integer cropNo) {
    	
        Optional<Object[]> cropDetailsOptional = cropRepo.getCropNameAndDate(cropNo, memberNo);

        if (cropDetailsOptional.isPresent()) {
            Object[] cropDetails = cropDetailsOptional.get();
            return ResponseEntity.ok(cropDetails);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    //작물 온습조도
    @GetMapping("/sensor-info")
    public ResponseEntity<List<Object[]>> getDiaryAndSensorInfo(
            @RequestParam Integer memberNo,
            @RequestParam String diaryDate,
            @RequestParam Integer cropNo) {
    	
	    List<Object[]> list = cropRepo.findDiaryAndSensorInfo(memberNo, diaryDate, cropNo);
		return ResponseEntity.status(HttpStatus.OK).body(list);
    }
}
