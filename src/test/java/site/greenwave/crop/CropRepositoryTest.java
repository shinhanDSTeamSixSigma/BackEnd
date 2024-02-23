package site.greenwave.crop;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Log4j2
public class CropRepositoryTest {
	
	@Autowired
	private CropRepository cropRepo;
	
	@Test
	void read() {
		Integer memberNo = 1;
		Integer cropNo = 1;
		
		Integer result = cropRepo.getCropState(memberNo, cropNo);
		log.info(String.valueOf("result: "+result));
	}

}
