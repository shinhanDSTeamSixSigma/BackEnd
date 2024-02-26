package site.greenwave.point.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import site.greenwave.point.dto.PaymentInfo;
import site.greenwave.point.service.BillService;

@CrossOrigin(origins = {"http://localhost:3000/","http://localhost/"})
@RestController
public class MobilePaymentController {

    @Autowired
    private BillService billService;

    @Value("${iamport.key}")
    private String restApiKey;

    @Value("${iamport.secret}")
    private String restApiSecret;

//    @PostMapping("/mobile-payment-callback")
//    public ResponseEntity<String> handleMobilePaymentCallback(@RequestBody Map<String, String> callbackData) {
//        // 아임포트 API 통해 결제 정보 검증
//        String impUid = callbackData.get("imp_uid");
//        String merchantUid = callbackData.get("merchant_uid");
//
//
//        try {
//            String accessToken = getAccessToken(); // 아임포트 API에 접근하기 위한 액세스 토큰 획득
//            PaymentInfo paymentInfo = getPaymentInfo(accessToken, impUid);
//
//            //DB에 결제 정보 저장
//            billService.recordPayment(paymentInfo);
//
//            // 성공적인 응답
//            return new ResponseEntity<>("Payment recorded successfully", HttpStatus.OK);
//        } catch (Exception e) {
//            // 오류 발생 시 예외 처리
//            return new ResponseEntity<>("Error while processing payment callback", HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // 아임포트 API를 통해 액세스 토큰을 얻어오는 메서드 (실제 코드에서는 보안적인 이슈로 안전한 방법으로 처리해야 함)
//    private String getAccessToken() {
//        // 아임포트 API를 통해 액세스 토큰을 얻어오는 로직을 작성
//        // (이 부분은 보안 상의 이유로 안전한 방식으로 구현되어야 함)
//        
//    	return iamportService.getAccessToken(restApiKey, restApiSecret);
//    }
//
//    // 아임포트 API를 통해 결제 정보를 가져오는 메서드 (실제 코드에서는 아임포트 API와 통신하여 데이터를 가져와야 함)
//    private PaymentInfo getPaymentInfo(String accessToken, String impUid) {
//        // 아임포트 API를 통해 결제 정보를 가져오는 로직을 작성
//        
//    	return iamportService.getPaymentInfo(accessToken, impUid);
//    }
}