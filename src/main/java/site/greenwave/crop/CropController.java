package site.greenwave.crop;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import site.greenwave.config.UserInfo;
import site.greenwave.config.UserInfoDto;

import java.util.List;

@RestController
@Slf4j
public class CropController {

    private final CropRepository cropRepository;
    private final ArduinoCropSensorRepository arduinoCropSensorRepository;

    public CropController(CropRepository cropRepository, ArduinoCropSensorRepository arduinoCropSensorRepository) {
        this.cropRepository = cropRepository;
        this.arduinoCropSensorRepository = arduinoCropSensorRepository;
    }

    @GetMapping("/crops")
    public ResponseEntity<List<CropEntity>> getCropsByMemberNo(@UserInfo UserInfoDto userInfoDto) {
        List<CropEntity> crops = cropRepository.findByMemberEntity(userInfoDto.getId());
        log.info("crops {} " , crops);
        crops.stream().forEach(x -> log.info(x.getCropNickname()));
        if (crops.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(crops, HttpStatus.OK);
    }

    @GetMapping("/cropLogs/{cropNo}")
    public ResponseEntity<List<CropSensorLogEntity>> getCropManureLog(@PathVariable Integer cropNo) {
        List<CropSensorLogEntity> cropManureLog = arduinoCropSensorRepository.findByCropEntity(cropNo);
        if (cropManureLog != null) {
            //return new ResponseEntity<>(cropManureLog, HttpStatus.OK);
            return new ResponseEntity<>(cropManureLog, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
