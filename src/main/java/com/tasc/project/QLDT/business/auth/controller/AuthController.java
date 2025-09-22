package com.tasc.project.QLDT.business.auth.controller;

import com.tasc.project.QLDT.business.auth.payload.request.LoginRequest;
import com.tasc.project.QLDT.business.auth.payload.response.LoginResponse;
import com.tasc.project.QLDT.business.auth.service.impl.AuthServiceImpl;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthController {
    AuthServiceImpl authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login (@Valid @RequestBody LoginRequest request){
        LoginResponse loginResponse = authService.signIn(request);
        return ResponseEntity.ok().body(loginResponse);
    }
}
