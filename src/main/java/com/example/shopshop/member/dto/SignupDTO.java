package com.example.shopshop.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    @NotEmpty(message = "유효하지 않은 이메일입니다.")
    private String email;

    @NotEmpty(message = "인증번호를 입력해주세요.")
    private String emailCheck;

    @NotNull(message = "패스워드를 입력해주세요")
    @Size(min = 3, max =20, message = "3자에서 20자까지 가능합니다.")
    private String password;

    @NotNull(message = "패스워드 확인을 입력해주세요")
    @Size(min = 3, max =20, message = "3자에서 20자까지 가능합니다.")
    private String passwordCheck;

    @AssertTrue(message ="패스워드와 일치하지 않습니다.")
    public boolean isPasswordMatch() {
        return password.equals(passwordCheck);
    }

    @NotBlank(message = "공백일 수 없습니다.")
    private String name;

    @NotBlank(message = "공백일 수 없습니다.")
    private String phone;

    @NotBlank(message = "공백일 수 없습니다.")
    private String address;

    public MemberDTO toEntity() {

        MemberDTO memberDTO = MemberDTO.builder()
                .email(this.email)
                .password(this.password)
                .name(this.name)
                .phone(this.phone)
                .address(this.address)
                .build();
        return memberDTO;
    }


}
