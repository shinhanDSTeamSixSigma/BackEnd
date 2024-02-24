package site.greenwave.crop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.greenwave.file.FileUtil;

@RestController
@Slf4j
public class CropFarmController {
	@Autowired
	CropFarmService service;
	@Autowired
	FileUtil fileutil;
	@Autowired
	CropRepository cropRepo;
	
	@GetMapping("/myCropsList")
	public Map MyCrops(/*@UserInfo UserInfoDto dto*/) {
		/*if(dto == null) {
			return null;
		}*/
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("liveCrops", service.getCropsWhatIHave(1));
		data.put("doneCrops", service.getDoneCropsWhatIHave(1));
		return data;
	}
	@GetMapping("/mypage/album/{crop_no}")
	public Map myPageAlbum(@PathVariable String crop_no){
		//return fileutil.getFilesFrom("CROP", Integer.parseInt(crop_no));
		Map<String, Object> data = new HashMap<String, Object>();
		int cropNo = 0;
		try {
			cropNo = Integer.parseInt(crop_no);
		}catch(Exception e) {
			
		}
		data.put("cropData", service.getCropNameAndDateFromCropNo(cropNo));
		data.put("images", fileutil.getFilesFrom("CROP", cropNo));
		return data;
	}
}
