package site.greenwave.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.greenwave.farm.entity.FarmEntity;

@Repository
public interface FarmRepositoy extends JpaRepository<FarmEntity,Integer> {

}
