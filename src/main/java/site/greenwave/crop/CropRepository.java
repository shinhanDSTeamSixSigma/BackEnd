package site.greenwave.crop;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<CropEntity, Integer> {
	
	//작물 상태
	@Query("SELECT ce.cropState FROM CropEntity ce WHERE ce.memberEntity.memberNo = :memberNo AND ce.cropNo = :cropNo")
	Integer getCropState(Integer memberNo, Integer cropNo);
	
	//작물 구매일
	@Query("SELECT ce.buyDate FROM CropEntity ce WHERE ce.memberEntity.memberNo = :memberNo AND ce.cropNo = :cropNo")
    Date getCropBuyDate(Integer memberNo, Integer cropNo);

    //작물 이름, 일자
    @Query("SELECT ce.cropNickname, ce.buyDate, ce.endDate FROM CropEntity ce WHERE ce.cropNo = :cropNo AND ce.memberEntity.memberNo = :memberNo")
    Optional<Object[]> getCropNameAndDate(Integer cropNo, Integer memberNo);

    //작물 온습조도 (CropSensorLogEntity 잘못된 클래스 이름 수정)
    @Query("SELECT date_format(csl.sensorTime,'%Y-%m-%d'), csl.thomer, csl.lumen, csl.soilHumid FROM CropEntity c " +
            "JOIN CropSensorLogEntity csl ON c.cropNo = csl.cropEntity.cropNo " +
            "WHERE c.memberEntity.memberNo = :memberNo " +
            "AND date_format(csl.sensorTime,'%Y-%m-%d') = :diaryDate " +
            "AND c.cropNo = :cropNo")
    List<Object[]> findDiaryAndSensorInfo(Integer memberNo, String diaryDate, Integer cropNo);

    @Query("SELECT ce from CropEntity ce WHERE ce.memberEntity.memberNo = :memberNo")
        // Param import 오류 가능성 있음
    List<CropEntity> findByMemberEntity(@org.springframework.data.repository.query.Param("memberNo") Integer memberNo);
}