package site.greenwave.diary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;

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
		
//	    // 게시글에 연결된 파일 조회
//	    Optional<FileEntity> optionalFile = fileRepo.findByFileManageNo(diaryId);
//	    
//	    optionalFile.ifPresent(file -> {
//	        // 파일 삭제
//	        fileRepo.delete(file);
//
//	        // 파일 시스템에서 실제 파일 삭제 작업 수행
//	        deleteFile(file.getPath());
//	    });

		Map<String, Object> result = new HashMap<>();
		result.put("result", "success");
		return result;
	}

//	private static boolean deleteFile(String filePath) {
//	    try {
//	        Path path = Paths.get(filePath);
//	        Files.deleteIfExists(path);
//	        return true;
//	    } catch (IOException e) {
//	        e.printStackTrace();
//	        return false;
//	    }
//	}

}