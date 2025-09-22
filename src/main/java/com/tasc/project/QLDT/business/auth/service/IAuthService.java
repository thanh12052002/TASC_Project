package com.tasc.project.QLDT.business.auth.service;

import com.tasc.project.QLDT.business.auth.payload.request.LoginRequest;
import com.tasc.project.QLDT.business.auth.payload.response.LoginResponse;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Transactional
public interface IAuthService {
    LoginResponse signIn (LoginRequest loginRequest);
}
