package com.example.shopshop.security.jwt;

public interface JwtProperties {

    String SECRET = "SECRET_CODE_OF_SHOP";
    int EXPIRATION_TIME = 600 * 1000;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
