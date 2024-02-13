package site.greenwave.dict;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins= {"http://localhost:3000/"})
@RestController
@RequestMapping("/api")
public class CropDictController {
	@Autowired
	private CropDictRepository cropDictRepo;
	
	@GetMapping("/crop-list")
	public List<CropDictEntity> listCrops() {
		return cropDictRepo.findAll();
	}
//	@GetMapping("/crop-detail/{cropDictNo}")
//    public ResponseEntity<CropDictEntity> getCropByCropDictNo(@PathVariable Integer cropDictNo) {
//        CropDictEntity crop = cropDictRepo.findByCropDictNo(cropDictNo);
//        if (crop != null) {
//            return new ResponseEntity<>(crop, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
