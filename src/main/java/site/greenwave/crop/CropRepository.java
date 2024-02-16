package site.greenwave.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CropRepository extends JpaRepository<CropEntity, Long> {
	
	//작물 상태
	@Query("SELECT ce.cropState FROM CropEntity ce WHERE ce.memberEntity.memberNo = :memberNo AND ce.cropNo = :cropNo")
	Integer getCropState(Integer memberNo, Integer cropNo);
}