package site.greenwave.diary.service;

import java.util.List;
import java.util.Map;

import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryService {
	//목록
	List<DiaryEntity> getDiaryInfo(Integer memberNo, Integer cropNo);
    //등록
    Map<String, Object> registerDiary(Map<String, Object> map);
    //수정
    void modifyDiary(Map<String, Object> map, Integer diaryNo);
    //삭제
    void deleteDiary(Integer diaryNo);
}
