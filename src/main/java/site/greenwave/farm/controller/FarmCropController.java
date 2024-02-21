package site.greenwave.farm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.greenwave.dict.CropDictEntity;
import site.greenwave.farm.dto.FarmCropDto;
import site.greenwave.farm.entity.FarmEntity;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm")
public class FarmCropController {

//    @PostMapping("/farmCrop")
//    public ResponseEntity<String> registFarmCrop(@RequestBody FarmCropDto farmCropDto){
//        Optional<FarmEntity> farmEntity =
//                Optional<CropDictEntity>
//        return null;
//    }
}
