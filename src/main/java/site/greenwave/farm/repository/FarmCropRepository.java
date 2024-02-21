package site.greenwave.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.greenwave.farm.entity.FarmCropEntity;

@Repository
public interface FarmCropRepository extends JpaRepository<FarmCropEntity, Integer> {
}
