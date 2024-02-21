package site.greenwave.farm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.greenwave.dict.CropDictEntity;
import site.greenwave.dict.CropDictRepository;
import site.greenwave.farm.entity.FarmCropEntity;

import java.util.Optional;

@Repository
public interface FarmCropRepository extends JpaRepository<FarmCropEntity, Integer> {


//    FarmCropEntity findByFarmNo(Integer farmNo);


}
