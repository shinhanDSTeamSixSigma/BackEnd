package site.greenwave.diary.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import site.greenwave.crop.CropEntity;
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

    //일기 등록
    @Override
    @Transactional
    public Map<String, Object> registerDiary(Map<String, Object> map) {
        Integer memberNo = Integer.parseInt(map.get("memberNo").toString());
        String content = map.get("content").toString();
        Integer cropNo = Integer.parseInt(map.get("cropNo").toString());

        String diaryDateString = map.get("diaryDate").toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date diaryDate;
        try {
            diaryDate = dateFormat.parse(diaryDateString);
        } catch (ParseException e) {
            // 날짜 파싱 예외 처리
            Map<String, Object> result = new HashMap<>();
            result.put("result", "error");
            result.put("message", "Failed to parse the date.");
            return result;
        }

        DiaryEntity diaryEntity = new DiaryEntity();
        MemberEntity memberEntity = new MemberEntity();
        CropEntity cropEntity = new CropEntity();

        memberEntity.setMemberNo(memberNo);
        diaryEntity.setMemberEntity(memberEntity);

        diaryEntity.setDiaryDate(diaryDate);
        diaryEntity.setContent(content);

        cropEntity.setCropNo(cropNo);
        diaryEntity.setCropEntity(cropEntity);

        diaryRepo.save(diaryEntity);

        Map<String, Object> result = new HashMap<>();
        result.put("result", "success");
        result.put("diaryNo", diaryEntity.getDiaryNo());
        return result;
    }
    
    //일기 수정
    @Override
    public void modifyDiary(Map<String, Object> map, Integer diaryNo) {
    	
    }

    //일기 삭제
    @Override
    public void deleteDiary(Integer diaryNo) {
        diaryRepo.deleteById(diaryNo);
    }
}
