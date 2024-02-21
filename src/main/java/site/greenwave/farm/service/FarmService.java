package site.greenwave.farm.service;

import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.dto.PageRequestDto;
import site.greenwave.farm.dto.PageResponseDto;

import java.util.List;

public interface FarmService {
    // 등록
    Integer register(FarmDto farmDto);

    // 상세 조회
    FarmDto get(Integer farmNo);

    // 수정
    void modify(FarmDto farmDto);
    // 삭제
    void delete(Integer farmNo);

    // 전체 조회
    List<FarmDto> getAll();

    // 페이징
    PageResponseDto<FarmDto> list(PageRequestDto pageRequestDto);
}
