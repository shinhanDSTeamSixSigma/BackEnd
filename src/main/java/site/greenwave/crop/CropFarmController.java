package site.greenwave.crop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.greenwave.config.UserInfo;
import site.greenwave.config.UserInfoDto;

@RestController
@Slf4j
public class CropFarmController {
	@Autowired
	CropFarmService service;
	
	@GetMapping("")
	public Map MyCrops(@UserInfo UserInfoDto dto) {
		if(dto == null) {
			return null;
		}
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("liveCrops", service.getCropsWhatIHave(dto.getId()));
		data.put("doneCrops", service.getDoneCropsWhatIHave(dto.getId()));
		return data;
	}
}
