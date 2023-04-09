package com.example.shopshop.etc.mailtest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NotEmpty
@NoArgsConstructor
@AllArgsConstructor
public class EmailRequest {

    private String to;
    private String subject;
    private String body;
}
