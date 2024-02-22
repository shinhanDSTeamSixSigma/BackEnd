package site.greenwave.farm.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.dto.PageRequestDto;
import site.greenwave.farm.dto.PageResponseDto;

@SpringBootTest
@Log4j2
class FarmServiceTest {

    @Autowired
    private FarmService farmService;

    // 저장
    @Test
    public void testRegister() throws Exception {



        for (int i = 10; i <= 23; i++) {

            FarmDto farmDto = FarmDto.builder()
                    .farmName("토뭉이네" + i)
                    .farmAddress("경기도 수원시 장안구 장안로" + i)
                    .farmContent("맛있는 감자 농장 " + i)
                    .farmDescription("누구나 좋아하는 유기농 감자 전문 농장입니다 " + i)
                    .farmPhone("010777777" + i)
                    .farmSize("250")
                    .farmCareer("15")
                    .farmOrderNum(20 + i)
                    .farmConnect("9시 ~ 22시")
                    .farmCategory("가지과")
                    .farmRating(5.0)
                    .reviewCnt(30 + i)
                    .memberNo(i)
                    .build();
            Integer farmNo = farmService.register(farmDto);
            log.info("FarmNo: " + farmNo);

        }
        //given
    }

    // 상세 조회
    @Test
    public void testGet() throws Exception {
        //given
        Integer farmNo = 2;
        FarmDto farmDto = farmService.get(farmNo);
        log.info(farmDto);
    }

    // 전체 조회
    @Test
    public void testGetAll() throws Exception {
        //given
        List<FarmDto> list = farmService.getAll();
        log.info(list);

    }

    // 수정
    @Test
    public void testModify() throws Exception {

        farmService.modify(FarmDto.builder()
                .farmNo(25)
                .farmName("토뭉토뭉이")
                .farmCareer("2년")
                .build());


    }

    // 삭제
    @Test
    public void testDelete() throws Exception {
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