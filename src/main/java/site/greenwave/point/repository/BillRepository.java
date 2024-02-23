package site.greenwave.point.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import site.greenwave.point.entity.BillEntity;

public interface BillRepository extends JpaRepository<BillEntity, Integer> {
}
