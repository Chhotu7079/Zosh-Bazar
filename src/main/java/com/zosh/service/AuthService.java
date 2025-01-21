package com.zosh.service;

import com.zosh.domain.User_Role;
import com.zosh.request.LoginRequest;
import com.zosh.response.AuthResponse;
import com.zosh.response.SignupRequest;

public interface AuthService {

    void sentLoginOtp(String email, User_Role role) throws Exception;
    String createUser(SignupRequest req) throws Exception;
    AuthResponse signing(LoginRequest req) throws Exception;
} 
