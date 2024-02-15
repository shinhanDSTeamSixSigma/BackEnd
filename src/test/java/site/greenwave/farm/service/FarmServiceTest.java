package site.greenwave.farm.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.multipart.MultipartFile;
import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.dto.PageRequestDto;
import site.greenwave.farm.dto.PageResponseDto;
import site.greenwave.farm.entity.FarmEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Log4j2
class FarmServiceTest {

    @Autowired
    private FarmService farmService;

    // 저장
    @Test
    public void testRegister() throws Exception{

        for (int i = 0; i < 10; i++) {
        FarmDto farmDto = FarmDto.builder()
                .farmName("토뭉이네")
                .farmAddress("경기도 수원시 장안구 장안로")
                .farmContent("맛있는 감자 농장")
                .farmDescription("누구나 좋아하는 유기농 감자 전문 농장입니다")
                .farmPhone("010-7777-7777")
                .farmSize("300평")
                .farmCareer("10년")
                .farmOrderNum(200)
                .farmConnect("9시 ~ 21시")
                .farmCategory("가지과")
                .farmRating(5.0)
                .reviewCnt(100)
                .memberNo(1)
                .build();
        Integer farmNo = farmService.register(farmDto);
            log.info("FarmNo: " + farmNo);

        }
        //given
    }

    // 상세 조회
    @Test
    public void testGet() throws Exception{
        //given
        Integer farmNo = 2;
        FarmDto farmDto = farmService.get(farmNo);
        log.info(farmDto);
    }
    // 전체 조회
    @Test
    public void testGetAll() throws Exception{
        //given
        List<FarmDto> list = farmService.getAll();
        log.info(list);

    }

    // 수정
    @Test
    public void testModify() throws Exception{

        farmService.modify(FarmDto.builder()
                .farmNo(25)
                .farmName("토뭉토뭉이")
                .farmCareer("2년")
                .build());


    }

    // 삭제
    @Test
    public void testDelete() throws Exception{
        //given
        Integer farmNo = 1;
        farmService.delete(farmNo);

    }


    // 페이징
    @Test
    public void testList() throws Exception {

        PageRequestDto requestDto = PageRequestDto.builder()
                .page(2)
                .size(5)
                .build();

        PageResponseDto<FarmDto> response = farmService.list(requestDto);
        log.info(response);



    }

}