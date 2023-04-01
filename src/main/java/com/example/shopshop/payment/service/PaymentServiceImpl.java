package com.example.shopshop.payment.service;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.util.Map;

@Service
public class PaymentServiceImpl implements PaymentService{

    private String impKey = "";

    private String impSecret = "";

    @Override
    public String getToken() throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/users/getToken");

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        conn.setDoOutput(true);
        JsonObject json = new JsonObject();

        json.addProperty("imp_key", impKey);
        json.addProperty("imp_secret", impSecret);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

        bw.write(json.toString());
        bw.flush();
        bw.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        String response = gson.fromJson(br.readLine(), Map.class).get("response").toString();

        System.out.println(response);

        String token = gson.fromJson(response, Map.class).get("access_token").toString();

        br.close();
        conn.disconnect();

        return token;
    }

    @Override
    public int paymentInfo(String imp_uid, String access_token) throws IOException {
        HttpsURLConnection conn = null;

        URL url = new URL("https://api.iamport.kr/payments/" + imp_uid);

        conn = (HttpsURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", access_token);
        conn.setDoOutput(true);

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));

        Gson gson = new Gson();

        Response response = gson.fromJson(br.readLine(), Response.class);

        br.close();
        conn.disconnect();

        return response.getPaymentInfo().getAmount();

    }


    @Override
    public void paymentCancel(String access_token, String imp_uid, String amount, String reason) {

    }

    @ToString
    @Getter
    private class Response {
        private PaymentInfo paymentInfo;
    }

    @ToString
    @Getter
    private class PaymentInfo {
        private int amount;
    }
}
