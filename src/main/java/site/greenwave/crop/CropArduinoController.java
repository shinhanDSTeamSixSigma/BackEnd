package site.greenwave.crop;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import site.greenwave.file.FileUtil;

@RestController
@RequestMapping("/fromArduino")
public class CropArduinoController {
	@Autowired
	ArduinoCropSensorRepository cropSensorRepo;
	
	@Autowired
	FileUtil fileUtil;
	
	@PostMapping("/sensor")
	public void SensorLog(@RequestParam Map map) {
		//Json 형식으로 보낼거니까
		//양식은 
		//"crop_no":1234
		//"temp": 1234
		//"humid": 21
		//이런식
		
		CropEntity cropEntity = new CropEntity();
		cropEntity.setCropNo(Integer.parseInt((String)map.get("crop_no")));
		CropSensorLogEntity sensorLogEntity = new CropSensorLogEntity();
		sensorLogEntity.setCropEntity(cropEntity);
		sensorLogEntity.setThomer(Integer.parseInt((String)map.get("thomer")));
		sensorLogEntity.setHumidity(Integer.parseInt((String)map.get("humidity")));
		sensorLogEntity.setLumen(Integer.parseInt((String)map.get("lumen")));
		sensorLogEntity.setSoilHumid(Integer.parseInt((String)map.get("solid_humid")));
		cropSensorRepo.save(sensorLogEntity);
	}
	@PostMapping("/image")
	public void ImageFile(@RequestParam String crop_no ,MultipartFile image) {
		fileUtil.UploadFile("CROP", Integer.parseInt(crop_no), image);
	}
	
}
