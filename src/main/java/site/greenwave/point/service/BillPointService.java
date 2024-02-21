package site.greenwave.point.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.greenwave.point.entity.BillEntity;
import site.greenwave.point.entity.PointEntity;
import site.greenwave.point.repository.BillRepository;
import site.greenwave.point.repository.PointRepository;

@Service
public class BillPointService {
    @Autowired
    private BillRepository billRepo;
    @Autowired
    private PointRepository pointRepo;

    @Transactional
    public void saveBillWithPoint(BillEntity billEntity, PointEntity pointEntity) {
        // 두 엔터티 간의 관계 설정
        billEntity.setPointEntity(pointEntity);
        pointEntity.setBillEntity(billEntity);

        // BillEntity 저장하면 PointEntity도 함께 저장
        billRepo.save(billEntity);
    }
}
