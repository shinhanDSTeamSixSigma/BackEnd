package site.greenwave;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.dict.repository.CropDictRepository;

@SpringBootTest
class CropCateTest {

	@Autowired
	CropDictRepository repo;
	
	@Test
	void contextLoads() {
		List<CropDictEntity> entity = repo.findAll();
		entity.stream().forEach(e->System.out.println(e));
	}

}
