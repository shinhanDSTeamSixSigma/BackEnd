package site.greenwave.farm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.dict.repository.CropDictRepository;
import site.greenwave.farm.entity.FarmCropEntity;

import java.util.Optional;

@Repository
public interface FarmCropRepository extends JpaRepository<FarmCropEntity, Integer> {


    FarmCropEntity findByFarmEntityFarmNo(Integer farmNo);


}
