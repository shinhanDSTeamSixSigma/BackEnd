package site.greenwave.point.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.greenwave.crop.CropEntity;
import site.greenwave.member.MemberEntity;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.entity.PointEntity;
import site.greenwave.point.repository.PointRepository;

@Service
public class PointService {
	
	@Autowired
    private PointRepository pointRepo;

    @Transactional
    public Map<String, Object> registerPoint(PointDto pointDto) {
    	
        // MemberEntity 및 CropEntity 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo(pointDto.getMemberNo());

        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropNo(pointDto.getCropNo());

        // DiaryEntity 생성
        PointEntity pointEntity = new PointEntity();
        pointEntity.setMemberEntity(memberEntity);
        pointEntity.setCropEntity(cropEntity);
        
        pointEntity.setPointValue(pointDto.getPointValue());
        pointEntity.setChangeValue(pointDto.getChangeValue());
        pointEntity.setChangeCause(pointDto.getChangeCause());

        pointRepo.save(pointEntity);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("poinNo", pointEntity.getPointNo());
        return result;
    }
}
