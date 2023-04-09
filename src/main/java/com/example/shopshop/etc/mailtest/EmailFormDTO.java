package com.example.shopshop.etc.mailtest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmailFormDTO {


    private String to;
    private String subject;
    private String body;
}
