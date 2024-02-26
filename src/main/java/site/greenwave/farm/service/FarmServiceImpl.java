package site.greenwave.farm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.dto.PageRequestDto;
import site.greenwave.farm.dto.PageResponseDto;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.farm.repository.FarmRepositoy;
import site.greenwave.member.entity.MemberEntity;
import site.greenwave.member.repository.MemberRepository;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class FarmServiceImpl implements FarmService {
    //자동 주입하려면 final
    private final ModelMapper modelMapper;
    private final FarmRepositoy farmRepositoy;
    private final MemberRepository memberRepository;


    // 등록
    @Override
    public Integer register(FarmDto farmDto) {
        log.info("----------");

        Optional<MemberEntity> memberEntity = memberRepository.findById(farmDto.getMemberNo());

        FarmEntity farmEntity = FarmEntity.builder()
                .farmName(farmDto.getFarmName())
                .farmAddress(farmDto.getFarmAddress())
                .farmContent(farmDto.getFarmContent())
                .farmDescription(farmDto.getFarmDescription())
                .farmPhone(farmDto.getFarmPhone())
                .farmSize(farmDto.getFarmSize())
                .farmCareer(farmDto.getFarmCareer())
                .farmOrderNum(farmDto.getFarmOrderNum())
                .farmConnect(farmDto.getFarmConnect())
                .farmCategory(farmDto.getFarmCategory())
                .farmRating(farmDto.getFarmRating())
                .reviewCnt(farmDto.getReviewCnt())
                // MemberEntity가 존재하지 않으면 예외를 던짐
                .memberEntity(memberEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                .build();

        FarmEntity savedFarm = farmRepositoy.save(farmEntity);
        return savedFarm.getFarmNo();
    }

    // 상세 조회
    @Override
    public FarmDto get(Integer farmNo) {
        Optional<FarmEntity> result = farmRepositoy.findById(farmNo);
        FarmEntity farmEntity = result.orElseThrow(() -> new EntityNotFoundException("Farm not found"));
        FarmDto farmDto = FarmDto.builder()
                .farmNo(farmNo)
                .farmName(farmEntity.getFarmName())
                .farmAddress(farmEntity.getFarmAddress())
                .farmContent(farmEntity.getFarmContent())
                .farmDescription(farmEntity.getFarmDescription())
                .farmPhone(farmEntity.getFarmPhone())
                .farmSize(farmEntity.getFarmSize())
                .farmCareer(farmEntity.getFarmCareer())
                .farmOrderNum(farmEntity.getFarmOrderNum())
                .farmConnect(farmEntity.getFarmConnect())
                .farmCategory(farmEntity.getFarmCategory())
                .farmRating(farmEntity.getFarmRating())
                .reviewCnt(farmEntity.getReviewCnt())
                .memberNo(farmEntity.getMemberEntity().getMemberNo())
                .build();


//        FarmDto farmDto = modelMapper.map(result.orElseThrow(), FarmDto.class);
        return farmDto;
    }

    // 전체 조회
    @Override
    public List<FarmDto> getAll() {
        List<FarmEntity> result = farmRepositoy.findAll();
        List<FarmDto> dtoList = result.stream().map(x -> modelMapper.map(x, FarmDto.class)).collect(Collectors.toList());
        return dtoList;
    }


    // 수정
    @Override
    public void modify(FarmDto farmDto) {
        Optional<MemberEntity> memberEntity = memberRepository.findById(farmDto.getMemberNo());

        Optional<FarmEntity> result = farmRepositoy.findById(farmDto.getFarmNo());
        FarmEntity farm = result.orElseThrow();

        FarmEntity modifyEntity = FarmEntity.builder()
                .farmNo(farm.getFarmNo())
                .farmName(farmDto.getFarmName())
                .farmAddress(farmDto.getFarmAddress())
                .farmContent(farmDto.getFarmContent())
                .farmDescription(farmDto.getFarmDescription())
                .farmPhone(farmDto.getFarmPhone())
                .farmSize(farmDto.getFarmSize())
                .farmCareer(farmDto.getFarmCareer())
                .farmOrderNum(farmDto.getFarmOrderNum())
                .farmConnect(farmDto.getFarmConnect())
                .farmCategory(farmDto.getFarmCategory())
                .farmRating(farmDto.getFarmRating())
                .reviewCnt(farmDto.getReviewCnt())
                .memberEntity(memberEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                .build();

        farmRepositoy.save(modifyEntity);

    }

    // 삭제
    @Override
    public void delete(Integer farmNo) {
        farmRepositoy.deleteById(farmNo);
    }


    // 페이징 리스트
    @Override
    public PageResponseDto<FarmDto> list(PageRequestDto pageRequestDto) {
        // PageRequest.of( ex) 0, 10 -> 1페이지(1 페이지는 0부터 시작해야함), 10개 항목)
        Pageable pageable = PageRequest.of(pageRequestDto.getPage() - 1,
                pageRequestDto.getSize(),
                Sort.by("farmNo"));

        Page<FarmEntity> result = farmRepositoy.findAll(pageable);
        List<FarmDto> dtoList = result.getContent().stream().map(x -> modelMapper.map(x, FarmDto.class)).collect(Collectors.toList());

        long totalCount = result.getTotalElements();

        // 정적 팩토리 메서드기 때문에 타입을 중간에 넣어야함 , FarmDto의 빌더 생성 및 반환
        PageResponseDto<FarmDto> responseDto = PageResponseDto.<FarmDto>withAll()
                .dtoList(dtoList)
                .pageRequestDto(pageRequestDto)
                .totalCount(totalCount)
                .build();

        return responseDto;
    }
    // 농장 이름으로 검색
    @Override
    public List<FarmDto> searchByName(String name) {
        List<FarmEntity> result = farmRepositoy.findByFarmNameContainingIgnoreCase(name);
        List<FarmDto> dtoList = result.stream().map(x -> modelMapper.map(x, FarmDto.class)).collect(Collectors.toList());
        return dtoList;
    }

}
