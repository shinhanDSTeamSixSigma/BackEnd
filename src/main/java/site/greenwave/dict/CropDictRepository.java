package site.greenwave.dict;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CropDictRepository extends JpaRepository<CropDictEntity, Integer>{

    Optional<CropDictEntity> findByCropName(String cropName);



}
