package com.hanghae99.goodssite.dto.user;

import com.hanghae99.goodssite.entity.UserRoleEnum;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignUpRequestDto {
    @Email
    private String email;
    private String password;
    private char gender;
    private String phone;
    private String address;
    private UserRoleEnum role;


}
