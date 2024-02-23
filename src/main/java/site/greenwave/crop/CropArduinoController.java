package site.greenwave.crop;

import java.util.Enumeration;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.file.FileUtil;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
@RequestMapping("/fromArduino")
public class CropArduinoController {
	@Autowired
	ArduinoCropSensorRepository cropSensorRepo;
	
	@Autowired
	FileUtil fileUtil;
	
	@PostMapping("/sensor")
	public void SensorLog(@RequestBody Map<String, String> map) {
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
		try {
			sensorLogEntity.setThomer(Integer.parseInt((String)map.get("thomer")));
		}
		catch(Exception e) {
			sensorLogEntity.setThomer(-255);
		}
		try {
			sensorLogEntity.setHumidity(Integer.parseInt((String)map.get("humidity")));
		}
		catch(Exception e) {
			sensorLogEntity.setHumidity(-255);
		}
		try {
			sensorLogEntity.setLumen(Integer.parseInt((String)map.get("lumen")));
		}
		catch(Exception e) {
			sensorLogEntity.setLumen(-255);
		}
		try {
			sensorLogEntity.setSoilHumid(Integer.parseInt((String)map.get("solid_humid")));
		}
		catch(Exception e) {
			sensorLogEntity.setSoilHumid(-255);
		}
		log.info(sensorLogEntity.toString());
		cropSensorRepo.save(sensorLogEntity);
	}
	@PostMapping(consumes = {"multipart/form-data"}, value = "/image/{crop_no}")
	public void ImageFile(@PathVariable String crop_no ,@RequestBody MultipartFile image, HttpServletRequest req) {
		//log.info(map.toString());
		//log.info(request.toString());
		Enumeration<String> headers = req.getHeaderNames();
		while (headers.hasMoreElements()) {
			String header =headers.nextElement(); 
			System.out.println(req.getHeader(header));
		}
		
		Enumeration<String> enums = req.getParameterNames();
		while (enums.hasMoreElements()) {
			String name = enums.nextElement();
			System.out.print("name:"+name);
//			System.out.println(" "+req.`);
		}
		fileUtil.UploadFile("CROP", Integer.parseInt(crop_no), image);
	}
	
}
