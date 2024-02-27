package site.greenwave.farm.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import site.greenwave.dict.dto.CropDictDTO;
import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.dict.repository.CropDictRepository;
import site.greenwave.farm.dto.FarmCropDto;
import site.greenwave.farm.dto.FarmDto;
import site.greenwave.farm.entity.FarmCropEntity;
import site.greenwave.farm.entity.FarmEntity;
import site.greenwave.farm.repository.FarmCropRepository;
import site.greenwave.farm.repository.FarmRepositoy;
import site.greenwave.file.FileUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/farm")
@Log4j2
public class FarmCropController {

    private final FarmRepositoy farmRepositoy;
    private final CropDictRepository cropDictRepository;
    private final FarmCropRepository farmCropRepository;
    private final FileUtil fileUtil;

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

    @GetMapping("/{farmNo}/farmCropGet")
    public ResponseEntity<Map<String,CropDictDTO>> getFarmCrop(@PathVariable(name = "farmNo") Integer farmNo){
    	
        // farmCropEntity에서 farmNo로 값 구하기
        FarmCropEntity farmCropEntity = farmCropRepository.findByFarmEntityFarmNo(farmNo);

        // getFarmCropNo값 구할 수 있다
        Integer cropNum = farmCropEntity.getCropDictEntity().getCropDictNo();
        Optional<CropDictEntity> cropDictEntity = cropDictRepository.findById(cropNum);
        CropDictEntity cropDict = cropDictEntity.orElse(null);

        CropDictDTO dto = new CropDictDTO(cropDict);
        dto.setImage(fileUtil.getFileFrom("DICT", dto.getCropDictNo()));
        log.info(dto);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("getResult",dto));
    }


    @DeleteMapping("/{farmNo}/farmCropDelete")
    public ResponseEntity<Map<String,String>> delete(@PathVariable(name = "farmNo") Integer farmNo){
        // farmCropEntity에서 farmNo로 값 구하기
        FarmCropEntity farmCropEntity = farmCropRepository.findByFarmEntityFarmNo(farmNo);
        Integer cropNum = farmCropEntity.getCropDictEntity().getCropDictNo();
        log.info(cropNum);
        Integer farmCropNum = farmCropEntity.getFarmCropNo();
        farmCropRepository.deleteById(farmCropNum);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", "Success"));
    }

    @PutMapping("/{farmNo}/farmCropModify")
    public ResponseEntity<Map<String, String>> modify(@RequestBody FarmCropDto farmCropDto, @PathVariable Integer farmNo) {
        Optional<FarmEntity> farmEntity = farmRepositoy.findById(farmNo);
        Optional<CropDictEntity> cropDictEntity = cropDictRepository.findByCropName(farmCropDto.getCropName());

        FarmCropEntity farmCropEntity = farmCropRepository.findByFarmEntityFarmNo(farmNo);
        farmCropEntity = FarmCropEntity.builder()
                .farmCropNo(farmCropEntity.getFarmCropNo())
                .farmEntity(farmEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                .cropDictEntity(cropDictEntity.orElseThrow(() -> new RuntimeException("MemberEntity not found")))
                .build();

        farmCropRepository.save(farmCropEntity);

        return ResponseEntity.status(HttpStatus.OK).body(Map.of("Result", "Success"));
    }

}
