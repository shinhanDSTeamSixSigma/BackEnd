package site.greenwave.point.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import site.greenwave.crop.CropDto;
import site.greenwave.point.dto.BillDto;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.service.BillService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/charge")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/register-bill")
    public ResponseEntity<Integer> registerBill(@RequestBody Map map) {
    	log.info("==========="+map.toString());
    	
    	BillDto dto = new BillDto();
    	
		Integer billNo = billService.registerBill(map);
		return new ResponseEntity<>(billNo, HttpStatus.OK);
    }
}
