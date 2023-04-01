package com.example.shopshop.payment.controller;

import com.example.shopshop.payment.service.PaymentService;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
@Log4j2
public class PaymentController {

    private String impKey = "";

    private String impSecret = "";

    @Autowired
    private PaymentService paymentService;

    private final IamportClient iamportClient;


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
}
