package site.greenwave.diary.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.diary.dto.DiaryDto;
import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer>{
	//일기 목록
	List<DiaryEntity> findByMemberEntityMemberNoAndCropEntityCropNoOrderByDiaryDateDesc(Integer memberNo, Integer cropNo);
	//일기 세부 내용
	List<DiaryEntity> findByDiaryNo(Integer diaryNo);
	
	//일기 내용
	List<DiaryEntity> findByMemberEntityMemberNoAndCropEntityCropNoAndDiaryDate(Integer memberNo, Integer cropNo, Date diaryDate);
}