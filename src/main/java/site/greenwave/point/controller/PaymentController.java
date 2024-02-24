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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import site.greenwave.point.dto.BillDto;
import site.greenwave.point.dto.PointDto;
import site.greenwave.point.service.BillService;

@Slf4j
@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
public class PaymentController {
	
    @Autowired
    private BillService billService;
    
    @Value("${iamport.key}")
    private String restApiKey;
    @Value("${iamport.secret}")
    private String restApiSecret;

    private IamportClient iamportClient;

    @PostConstruct
    public void init() {
        this.iamportClient = new IamportClient(restApiKey, restApiSecret);
    }

    @PostMapping("/verifyIamport")
    public ResponseEntity<String> paymentByImpUid(@RequestBody Map<String, Object> requestBody) throws IamportResponseException, IOException {
    	log.info("==========="+requestBody.toString());
    	
    	ObjectMapper objectMapper = new ObjectMapper();
        BillDto billDto = objectMapper.convertValue(requestBody.get("billDto"), BillDto.class);
        PointDto pointDto = objectMapper.convertValue(requestBody.get("pointDto"), PointDto.class);

        billService.registerBillAndPoint(billDto, pointDto);
    	
//    	CropDto dto = new CropDto();
//    	
//		Integer cropNo = cropService.registerCrop(map);
    	return new ResponseEntity<>("Bill and Point saved successfully", HttpStatus.OK);
    }
    @PostMapping("/verifyAndRecord")
    public ResponseEntity<String> verifyAndRecordPayment(@RequestParam("imp_uid") String impUid,
                                                        @RequestParam("merchant_uid") String merchantUid) {
        
//        String accessToken = iamportService.getAccessToken(restApiKey, restApiSecret);
//        PaymentInfo paymentInfo = iamportService.getPaymentInfo(accessToken, impUid);
//        paymentService.recordPayment(paymentInfo);
        
        return new ResponseEntity<>("Payment recorded successfully", HttpStatus.OK);
    }
}