package site.greenwave.dict.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.dict.dto.CropDictDTO;
import site.greenwave.dict.entity.CropDictEntity;
import site.greenwave.dict.repository.CropDictRepository;
import site.greenwave.farm.util.CustomFileUtil;
import site.greenwave.file.FileUtil;

@CrossOrigin(origins= {"http://localhost:3000/","http://192.168.0.51:3000/"})
@RestController
@RequestMapping("/crop-dict")
public class CropDictController {
	@Autowired
	private CropDictRepository cropDictRepo;
	@Autowired
	FileUtil imagefile;

	
	@GetMapping("/list")
	public List<CropDictDTO> listCrops() {
		List<CropDictEntity> dicts= cropDictRepo.findAll();
		List<CropDictDTO> objs = new ArrayList<CropDictDTO>();
		for(CropDictEntity e : dicts) {
			CropDictDTO dto =new CropDictDTO(e);
			dto.setImage(imagefile.getFileFrom("DICT", dto.getCropDictNo()));
			objs.add(dto);
		}
		return objs;
	}

	@GetMapping("/listAll")
	public List<CropDictEntity> listAll(){
		return cropDictRepo.findAll();
	}

	@GetMapping("/detail/{cropDictNo}")
	public CropDictDTO getCropDetail(@PathVariable Integer cropDictNo) {
	    Optional<CropDictEntity>  dict = cropDictRepo.findByCropDictNo(cropDictNo);

	    CropDictDTO dto = new CropDictDTO(dict.orElseThrow(() -> new RuntimeException("MemberEntity not found")));
	    dto.setImage(imagefile.getFileFrom("DICT", cropDictNo));
	    return dto;
	}


	
}
