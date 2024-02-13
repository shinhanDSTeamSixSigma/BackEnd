package site.greenwave.farm;

import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
@Log4j2
public class FarmRepositoyTest {
    @Autowired
    private FarmRepositoy farmRepositoy;

    @Test
    public void test1() throws Exception{
        //given
        log.info("---------");
        for (int i = 0; i < 50; i++) {
            FarmEntity farmEntity = FarmEntity.builder()
                    .farmName("토심이네 " + i)
                    .farmAddress("경기도 수원시 장안구 장안로 " + i +"길")
                    .farmContent("맛좋은 농장 " + i)
                    .farmDescription("경험많은 토심이네 농부 " + i )
                    .farmPhone("010-3333-333"+i)
                    .farmSize("10"+i+"평")
                    .farmCareer("1"+i+"년")
                    .farmOrderNum(100)
                    .farmConnect("10시 ~ 20시")
                    .farmCategory("미나리과")
                    .farmRating(4.0 + 0.1* (double) i)
                    .build();
            farmRepositoy.save(farmEntity);

        }

    }
    @Test
    public void testRead() throws Exception {
        //given
        Integer farm_no = 22;
        Optional<FarmEntity> result = farmRepositoy.findById(farm_no);
        List<FarmEntity> result2 = farmRepositoy.findAll();
//        result.stream().forEach((x)->log.info(x.getFarmCategory()));
        result2.stream().forEach((x)->log.info(x.getFarmName()));


//        log.info(farmEntity.getFarmName());


    }
    @Test
    public void testModify() throws Exception{
        //given
        Integer farm_no = 22;
        Optional<FarmEntity> result = farmRepositoy.findById(farm_no);
        FarmEntity farmEntity = result.orElseThrow();
        farmEntity.setFarmCategory("오이과");
        farmRepositoy.save(farmEntity);


    }
    @Test
    public void testDelete() throws Exception{
        //given
        Integer farm_no = 30;
        farmRepositoy.deleteById(farm_no);

    }

    @Test
    public void testPaging() throws Exception{
        //given
        Pageable pageable = PageRequest.of(0, 10, Sort.by("farmNo").descending());
        Page<FarmEntity> result = farmRepositoy.findAll(pageable);
        log.info("Total elements: {}", result.getTotalElements());
        result.getContent().stream().forEach(a -> log.info(a.toString()));
    }

}