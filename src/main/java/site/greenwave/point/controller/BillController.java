package site.greenwave.point.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.point.dto.BillDto;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.service.BillService;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
@RequestMapping("/charge")
public class BillController {
    @Autowired
    private BillService billService;

    @PostMapping("/saveBillAndPoint")
    public ResponseEntity<String> saveBillAndPoint(@RequestBody BillDto billDto, @RequestBody PointDto pointDto) {
    	billService.registerBillAndPoint(billDto, pointDto);
        return new ResponseEntity<>("Bill and Point saved successfully", HttpStatus.OK);
    }

}
