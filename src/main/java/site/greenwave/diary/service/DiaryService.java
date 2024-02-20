package site.greenwave.diary.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import site.greenwave.diary.dto.DiaryDto;
import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryService {
	//목록
	List<DiaryEntity> getDiaryInfo(Integer memberNo, Integer cropNo);
	//세부 내역
	List<DiaryEntity> getDetail(Integer diaryNo);
    //등록
    Map<String, Object> registerDiary(DiaryDto diaryDto);
    //수정
    void modifyDiary(DiaryDto diaryDto);
    //삭제
    void deleteDiary(Integer diaryNo);
    
    //날짜 조건 목록
    List<DiaryEntity> getDiaryInfoByDate(Integer memberNo, Integer cropNo, Date diaryDate);
}
