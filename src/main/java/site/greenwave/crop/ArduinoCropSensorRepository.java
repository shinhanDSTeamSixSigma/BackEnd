package site.greenwave.crop;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ArduinoCropSensorRepository extends JpaRepository<CropSensorLogEntity, Integer> {
	
}
