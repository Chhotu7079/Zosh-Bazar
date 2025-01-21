package com.zosh.request;

import com.zosh.domain.User_Role;

import lombok.Data;


@Data
public class LoginOtpRequest {

    private String email;
    private String otp;
    private User_Role role;
}
