package site.greenwave.diary.repository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import site.greenwave.diary.entity.DiaryEntity;

public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer>{
	//일기 목록
	List<DiaryEntity> findByMemberEntityMemberNoAndCropEntityCropNoOrderByDiaryDateDesc(Integer memberNo, Integer cropNo);
	//일기 세부 내용
	List<DiaryEntity> findByDiaryNo(Integer diaryNo);
	
	//일기 내용
    //날짜 조건있는 일기 정보
    @Query("SELECT d FROM DiaryEntity d " +
            "WHERE d.memberEntity.memberNo = :memberNo " +
            "AND d.cropEntity.cropNo = :cropNo " +
            "AND date_format(d.diaryDate,'%Y-%m-%d') = :diaryDate")
	List<DiaryEntity> findByMemberEntityMemberNoAndCropEntityCropNoAndDiaryDate(Integer memberNo, Integer cropNo, String diaryDate);
	
	//일기 목록 조건(온습도 포함)
	@Query("SELECT de, csl, ce.buyDate FROM DiaryEntity de " +
	           "LEFT JOIN CropSensorLogEntity csl ON de.cropEntity.cropNo = csl.cropEntity.cropNo " +
	           "LEFT JOIN CropEntity ce ON de.cropEntity.cropNo = ce.cropNo " +
	           "WHERE de.memberEntity.memberNo = :memberNo " +
	           "AND de.cropEntity.cropNo = :cropNo " +
	           "AND date_format(de.diaryDate,'%Y-%m-%d') = date_format(csl.sensorTime,'%Y-%m-%d') " +
	           "ORDER BY de.diaryDate DESC")
	List<Object[]> getDiaryWithCropSensorLog(Integer memberNo, Integer cropNo);
	
	//일기 세부 목록 조건(온습도 포함)
	@Query("SELECT de, csl, ce.buyDate FROM DiaryEntity de " +
		       "JOIN CropSensorLogEntity csl ON de.cropEntity.cropNo = csl.cropEntity.cropNo " +
		       "LEFT JOIN CropEntity ce ON de.cropEntity.cropNo = ce.cropNo " +
		       "WHERE de.diaryNo = :diaryNo " +
		       "AND date_format(de.diaryDate,'%Y-%m-%d') = date_format(csl.sensorTime,'%Y-%m-%d')")
	List<Object[]> getDiaryAndSensorLog(Integer diaryNo);

}