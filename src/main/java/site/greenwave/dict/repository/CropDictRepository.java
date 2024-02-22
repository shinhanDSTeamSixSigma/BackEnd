package site.greenwave.dict.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import site.greenwave.dict.entity.CropDictEntity;

import java.util.Optional;

public interface CropDictRepository extends JpaRepository<CropDictEntity, Integer>{

    Optional<CropDictEntity> findByCropName(String cropName);
    Optional<CropDictEntity> findByCropDictNo(Integer cropDictNo);


}
