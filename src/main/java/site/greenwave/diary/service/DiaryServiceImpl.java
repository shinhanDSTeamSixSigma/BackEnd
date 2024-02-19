package site.greenwave.diary.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.greenwave.crop.CropEntity;
import site.greenwave.diary.dto.DiaryDto;
import site.greenwave.diary.entity.DiaryEntity;
import site.greenwave.diary.repository.DiaryRepository;
import site.greenwave.member.MemberEntity;

@Service
public class DiaryServiceImpl implements DiaryService {

    @Autowired
    private DiaryRepository diaryRepo;
    
    //일기 리스트
    @Override
    public List<DiaryEntity> getDiaryInfo(Integer memberNo, Integer cropNo) {
        return diaryRepo.findByMemberEntityMemberNoAndCropEntityCropNo(memberNo, cropNo);
    }
    
    //날짜 조건있는 일기 정보
    public List<DiaryEntity> getDiaryInfoByDate(Integer memberNo, Integer cropNo, Date diaryDate) {
    	return diaryRepo.findByMemberEntityMemberNoAndCropEntityCropNoAndDiaryDate(memberNo, cropNo, diaryDate);
    }

    //일기 등록
    @Override
    @Transactional
    public Map<String, Object> registerDiary(DiaryDto diaryDto) {
    	
    	// 날짜 파싱
        Date diaryDate = diaryDto.getDiaryDate();

        // MemberEntity 및 CropEntity 생성
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberNo(diaryDto.getMemberNo());

        CropEntity cropEntity = new CropEntity();
        cropEntity.setCropNo(diaryDto.getCropNo());

        // DiaryEntity 생성
        DiaryEntity diaryEntity = new DiaryEntity();
        diaryEntity.setMemberEntity(memberEntity);
        diaryEntity.setCropEntity(cropEntity);
        
        diaryEntity.setDiaryDate(diaryDate);
        diaryEntity.setContent(diaryDto.getContent());

        diaryRepo.save(diaryEntity);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("diaryNo", diaryEntity.getDiaryNo());
        return result;
    }
    
    //일기 수정
    @Override
    @Transactional
    public void modifyDiary(DiaryDto diaryDto) {
    	Optional<DiaryEntity> modifyDiary = diaryRepo.findById(diaryDto.getDiaryNo());
    	
        if (modifyDiary.isPresent()) {
            DiaryEntity existingDiary = modifyDiary.get();
            existingDiary.setContent(diaryDto.getContent());
            
            try {
                diaryRepo.save(existingDiary);
            } catch (Exception e) {
                // 적절한 에러 핸들링
                e.printStackTrace(); // 또는 로깅 등
                throw new RuntimeException("일기 수정 중 오류가 발생했습니다.");
            }
        } else {
            // 일기가 존재하지 않을 경우의 처리
            throw new RuntimeException("해당 일기를 찾을 수 없습니다.");
        }
    }

    //일기 삭제
    @Override
    public void deleteDiary(Integer diaryNo) {
        diaryRepo.deleteById(diaryNo);
    }
}
