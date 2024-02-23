package site.greenwave.point.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.point.dto.BillDto;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.dto.VerifyIamportRequest;
import site.greenwave.point.service.BillService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
public class PaymentController {

    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;
    
    @Autowired
    private BillService billService;


    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/verifyIamport")
    public ResponseEntity<String> paymentByImpUid(@RequestBody Map map) throws IamportResponseException, IOException {
    	log.info("==========="+map.toString());
    	
    	
//    	BillDto billDto = map.getBillDto();
//      PointDto pointDto = map.getPointDto();
//
//    	billService.registerBillAndPoint(billDto, pointDto);
    	return new ResponseEntity<>("Bill and Point saved successfully", HttpStatus.OK);
    }
}