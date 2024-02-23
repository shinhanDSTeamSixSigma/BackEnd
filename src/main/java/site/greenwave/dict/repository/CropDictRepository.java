package site.greenwave.dict.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.dict.entity.CropDictEntity;

public interface CropDictRepository extends JpaRepository<CropDictEntity, Integer>{

	CropDictEntity findByCropDictNo(Integer cropDictNo);
	
}
