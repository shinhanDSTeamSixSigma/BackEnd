package site.greenwave.point.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.greenwave.crop.CropEntity;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.point.dto.BillDto;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.entity.BillEntity;
import site.greenwave.point.entity.PointEntity;

@Service
public class BillService {
	@Autowired
    private BillAndPointService billAndPoint;

    public void registerBillAndPoint(BillDto billDto, PointDto pointDto) {
        BillEntity billEntity = new BillEntity();
        PointEntity pointEntity = new PointEntity();

        // 데이터 설정
        billEntity.setFinalValue(billDto.getFinalValue());
        billEntity.setOriginValue(billDto.getOriginValue());
        billEntity.setDiscountValue(billDto.getDiscountValue());
        billEntity.setBillDiv(billDto.getBillDiv());
        
        pointEntity.setPointValue(pointDto.getPointValue());
        pointEntity.setChangeValue(pointDto.getChangeValue());
        pointEntity.setChangeCause(pointDto.getChangeCause());
        
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo(billDto.getMemberNo());

        // MemberEntity를 BillEntity와 PointEntity에 설정
        billEntity.setMemberEntity(memberEntity);
        pointEntity.setMemberEntity(memberEntity);
        
        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropNo(pointDto.getCropNo());
        pointEntity.setCropEntity(cropEntity);


        // BillEntity와 PointEntity를 함께 저장하는 서비스 메소드 호출
        billAndPoint.saveBillWithPoint(billEntity, pointEntity);
    }
}
