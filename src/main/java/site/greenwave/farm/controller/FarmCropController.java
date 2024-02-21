package site.greenwave.farm.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.greenwave.dict.CropDictEntity;
import site.greenwave.dict.CropDictRepository;
import site.greenwave.farm.dto.FarmCropDto;
import site.greenwave.farm.entity.FarmCropEntity;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.farm.repository.FarmCropRepository;
import site.greenwave.farm.repository.FarmRepositoy;

import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm")
public class FarmCropController {

    private final FarmRepositoy farmRepositoy;
    private final CropDictRepository cropDictRepository;
    private final FarmCropRepository farmCropRepository;

    @PostMapping("/farmCrop")
    public ResponseEntity<Map<String,Integer>> registFarmCrop(@RequestBody FarmCropDto farmCropDto){
        Optional<FarmEntity> farmEntity = farmRepositoy.findById(farmCropDto.getFarmNo());
        Optional<CropDictEntity> cropDictEntity = cropDictRepository.findByCropName(farmCropDto.getCropName());


        FarmCropEntity farmCropEntity = FarmCropEntity.builder()
                        .farmEntity(farmEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                        .cropDictEntity(cropDictEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                        .build();

        FarmCropEntity savedFarm = farmCropRepository.save(farmCropEntity);
               Integer farmCropNo = savedFarm.getFarmCropNo();
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("작물 등록",farmCropNo));
    }

//    @GetMapping("/{farmNo}/farmCropGet")
//    public ResponseEntity<String> getFarmCrop(@PathVariable(name = "farmNo") Integer farmNo){
//        farmCropRepository.findByFarmNo(farmNo);
//        return ResponseEntity.status(HttpStatus.OK).body("getResult");
//    }
}
