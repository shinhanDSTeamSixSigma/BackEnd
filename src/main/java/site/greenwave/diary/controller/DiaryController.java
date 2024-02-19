package site.greenwave.diary.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.diary.dto.DiaryDto;
import site.greenwave.diary.entity.DiaryEntity;
import site.greenwave.diary.service.DiaryService;
import site.greenwave.file.FileUtil;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/diary")
public class DiaryController {
	@Autowired
    private DiaryService diaryService;
	@Autowired
	private FileUtil file;

	//일기 리스트
	@GetMapping("/list")
	public ResponseEntity<List<DiaryEntity>> getDiaryInfo(
			@RequestParam("memberNo") Integer memberNo,
			@RequestParam("cropNo") Integer cropNo){
		
		List<DiaryEntity> list = diaryService.getDiaryInfo(memberNo, cropNo);
		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
	
	//일기 등록
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody DiaryDto diaryDto) {
		Map<String, Object> result = diaryService.registerDiary(diaryDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", result.get("diaryNo")));
    }
    
    //일기 수정
    @PutMapping("/modify/{diaryNo}")
    public ResponseEntity<Map<String, Object>> modify(
    		@RequestBody DiaryDto diaryDto,
    		@PathVariable Integer diaryNo) {
    	
    	diaryDto.setDiaryNo(diaryNo);
        diaryService.modifyDiary(diaryDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", "Success"));
    }
    
    //일기 삭제
    @PostMapping("/delete/{diaryNo}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Integer diaryNo) {
        diaryService.deleteDiary(diaryNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Map.of("Result", "Success"));
    }
}