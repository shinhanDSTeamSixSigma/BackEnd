package site.greenwave.farm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import site.greenwave.farm.entity.FarmEntity;

import java.util.List;

@Repository
public interface FarmRepositoy extends JpaRepository<FarmEntity,Integer> {
    // 농장 이름으로 부분 일치 검색
    List<FarmEntity> findByFarmNameContainingIgnoreCase(String name);

}
