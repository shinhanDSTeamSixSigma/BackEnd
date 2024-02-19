package site.greenwave.diary.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.diary.entity.DiaryEntity;
import site.greenwave.diary.service.DiaryService;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/calendar")
public class CalendarController {
	@Autowired
    private DiaryService diaryService;
	
	//날짜 기준 일기 내역
	@GetMapping("/list")
	public ResponseEntity<List<DiaryEntity>> getDiaryInfoByDate(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo,
			@RequestParam("diaryDate") Date diaryDate){
		
		List<DiaryEntity> list = diaryService.getDiaryInfoByDate(memberNo, cropNo, diaryDate);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
