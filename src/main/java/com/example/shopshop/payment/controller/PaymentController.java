package com.example.shopshop.payment.controller;

import com.example.shopshop.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

    private final IamportClient iamportClient;
    private String impKey = "4228841281505242";
    private String impSecret = "CumIt8Nn2yVNmU54h22OxdXN2Qfu2wZ8BeUn4ysoCws8mVWqPsF3hu45GD6bbB8uQ1lOgyLq8W22GmJx";
    @Autowired
    private PaymentService paymentService;


    public PaymentController() {
        this.iamportClient = new IamportClient(impKey, impSecret);
    }

    @PostMapping("/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(@PathVariable String imp_uid) throws IamportResponseException, IOException {
        String token = paymentService.getToken();
        log.info("token info : " + token);
        log.info("paymentByImpUid : " + imp_uid);
        return iamportClient.paymentByImpUid(imp_uid);
    }

    // 카드 결제 성공 후
    @PostMapping("/order/payment/complete")
    public ResponseEntity<String> paymentComplete(HttpSession session, long totalPrice) throws IOException {


        // 1. 아임포트 API 키와 SECRET키로 토큰을 생성

        // 2. 토큰으로 결제 완료된 주문정보를 가져옴

        // 3. 로그인하지 않았는데 사용포인트가 0 이상일경우 결제 취소

        // 4. 로그인 사용자가 현재포인트보다 사용포인트가 많을 경우 결제 취소

        // 5. DB에서 실제 계산되어야 할 가격가져오기

        // 6. 결제 완료된 금액과 실제 계산되어야 할 금액이 다를경우 결제 취소

        // 7. 결제에러시 결제 취소


        return new ResponseEntity<>(HttpStatus.OK);
    }
}
