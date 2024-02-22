package site.greenwave.crop;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.member.entity.MemberEntity;

@Service
public class CropService {

    @Autowired
    private CropRepository cropRepo;

    @Transactional
    public Integer registerCrop(Map map) {
    
        // MemberEntity, FarmEntity, DictEntity 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo((Integer)map.get("memberNo"));

        FarmEntity farmEntity = new FarmEntity();
        farmEntity.setFarmNo((Integer)map.get("farmNo"));
        
        CropDictEntity cropDictEntity = new CropDictEntity();
        cropDictEntity.setCropDictNo((Integer)map.get("dictNo"));

        // CropEntity 생성
        CropEntity cropEntity = new CropEntity();
        cropEntity.setMemberEntity(memberEntity);
        cropEntity.setFarmEntity(farmEntity);
        cropEntity.setCropDictEntity(cropDictEntity);
        
        cropEntity.setCropNickname((String)map.get("cropNickname"));
        cropEntity.setCropState((Integer)map.get("cropState"));

        CropEntity savedCropEntity = cropRepo.save(cropEntity);
        
        return savedCropEntity.getCropNo();
    }

	public CropEntity getCropByCropNo(Integer cropNo) {
		return cropRepo.findById(cropNo).orElse(null);
	}
}