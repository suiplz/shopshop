package com.example.shopshop.payment.service;

import java.io.IOException;

public interface PaymentService {

    String getToken() throws IOException;

    int paymentInfo(String imp_uid, String access_token) throws IOException;

    void paymentCancel(String access_token, String imp_uid, int amount, int ordersPrice) throws IOException;

}
