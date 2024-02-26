package site.greenwave.diary;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.crop.CropEntity;
import site.greenwave.diary.dto.DiaryDto;
import site.greenwave.diary.entity.DiaryEntity;
import site.greenwave.diary.repository.DiaryRepository;
import site.greenwave.diary.service.DiaryService;
import site.greenwave.member.entity.MemberEntity;


@SpringBootTest
@Slf4j
@Log4j2
public class DiaryTest {
	
    @Autowired
    private DiaryRepository diaryRepo;
    @Autowired
    private DiaryService diaryService;
    
    
    //목록
    @Test
    void list() {
	    Integer memberNo = 1;
	    Integer cropNo = 1;
	    
    	/*List<DiaryEntity> entity = diaryRepo.findByMemberEntityMemberNoAndCropEntityCropNo(memberNo, cropNo);
    	entity.forEach(x -> log.info("entity: "+String.valueOf(x)));*/
    }
    //등록
	@Test
	void register() {
	    Integer memberNo = 1;
	    Integer cropNo = 1;
	    String dateString = "2024-02-30";

	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	    Date diaryDate;
	    try {
	        diaryDate = dateFormat.parse(dateString);
	    } catch (ParseException e) {
	        // 유효하지 않은 날짜 처리
	        System.out.println("Invalid date format");
	        return;
	    }

	    for (int i = 0; i < 1; i++) {
	        DiaryEntity diaryEntity = new DiaryEntity();
	        MemberEntity memberEntity = new MemberEntity();
	        CropEntity cropEntity = new CropEntity();
	
	        memberEntity.setMemberNo(memberNo);
	        diaryEntity.setMemberEntity(memberEntity);
	
//	        diaryEntity.setDiaryDate(diaryDate);
	        diaryEntity.setContent("안녕안녕");
	
	        cropEntity.setCropNo(cropNo);
	        diaryEntity.setCropEntity(cropEntity);
		    	
	    	diaryRepo.save(diaryEntity);
	    }
    }
	//수정
	@Test
	void modify() {
		DiaryDto diaryDto = new DiaryDto();
		diaryDto.setDiaryNo(7);
		diaryDto.setContent("안녕안녕3");
		
		diaryService.modifyDiary(diaryDto);
	}
	//삭제
	@Test
	void delete() {
		Integer diayrNo = 8;
		diaryRepo.deleteById(diayrNo);
	}
}
