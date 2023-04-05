package com.example.shopshop.handler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CMRespDTO<T> {

    private int code;// 1(success), -1(fail)

    private String message;

    private T data;
}
