package site.greenwave.crop;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArduinoCropSensorRepository extends JpaRepository<CropSensorLogEntity, Integer> {
	@Query("SELECT ce from CropSensorLogEntity ce where ce.cropEntity.cropNo = :cropNo")
    List<CropSensorLogEntity> findByCropEntity(@Param("cropNo") Integer cropNo);
}
