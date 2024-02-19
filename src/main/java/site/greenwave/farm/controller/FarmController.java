package site.greenwave.farm.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.dto.PageRequestDto;
import site.greenwave.farm.dto.PageResponseDto;
import site.greenwave.farm.service.FarmService;
import site.greenwave.member.MemberEntity;
import site.greenwave.member.MemberRepository;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/farm")
public class FarmController {

    private final FarmService service;
    private final MemberRepository memberRepository;

    // 농장 상세
    @GetMapping("/{farmNo}")
    public ResponseEntity<FarmDto> get(@PathVariable(name = "farmNo") Integer farmNo) {

        FarmDto farmDto = service.get(farmNo);

        return ResponseEntity.status(HttpStatus.OK).body(farmDto);
    }

    // 농장 전체
    @GetMapping("/farm-all")
    public ResponseEntity<List<FarmDto>> getAll() {
        List<FarmDto> list = service.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // 회원데이터
    @GetMapping("member-detail")
    public ResponseEntity<List<MemberEntity>> getMember(){
        List<MemberEntity> list = memberRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(list);

    }

    // 농장 페이징
    @GetMapping("/list")
    public ResponseEntity<PageResponseDto<FarmDto>> getPaging(PageRequestDto pageRequestDto) {
        // 코드 안적으면 pageRequestDto는 default 값 들어감

        PageResponseDto<FarmDto> list = service.list(pageRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    // 농장 등록
    @PostMapping("/register")
    public ResponseEntity<Map<String, Integer>> register(@RequestBody FarmDto farmDto) {
        log.info("FarmDto : " + farmDto);
        Integer farmNo = service.register(farmDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("FarmNO", farmNo));
    }

    // 농장 수정
    @PutMapping("/{farmNo}")
    public ResponseEntity<Map<String, String>> modify(@RequestBody FarmDto farmDto, @PathVariable Integer farmNo) {
        farmDto.setFarmNo(farmNo);
        log.info("Modify : " + farmDto);
        service.modify(farmDto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", "Success"));
    }

    // 농장 삭제
    @DeleteMapping("/{farmNo}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable(name = "farmNo") Integer farmNo) {
        log.info("Delete : " + farmNo);
        service.delete(farmNo);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", "Success"));
    }
}
