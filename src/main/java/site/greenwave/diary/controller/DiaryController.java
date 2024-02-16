package site.greenwave.diary.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import site.greenwave.diary.repository.DiaryRepository;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/crop-diary")
public class DiaryController {
	@Autowired
	private DiaryRepository diaryRepo;
	
	@Transactional
	@PostMapping("/delete")
	public Map<String, Object> delete(@RequestBody Map map) {
		Integer diaryId = (Integer) map.get("no");
		
		//게시글 삭제
		diaryRepo.deleteById(diaryId);
		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}
}