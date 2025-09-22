package com.tasc.project.QLDT.business.auth.service.impl;

import com.tasc.project.QLDT.business.auth.mapper.UserDetailsConvert;
import com.tasc.project.QLDT.business.auth.payload.request.LoginRequest;
import com.tasc.project.QLDT.business.auth.payload.response.LoginResponse;
import com.tasc.project.QLDT.business.auth.payload.response.UserInfoDto;
import com.tasc.project.QLDT.business.auth.service.IAuthService;
import com.tasc.project.QLDT.business.refreshtoken.service.RefreshTokenService;
import com.tasc.project.QLDT.business.user.repository.IUserRepository;
import com.tasc.project.QLDT.model.auth.RefreshToken;
import com.tasc.project.QLDT.security.jwt.JwtUtils;
import com.tasc.project.QLDT.security.services.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthServiceImpl {
    private final AuthenticationManager authenticationManager;
    private final IUserRepository userRepository;
    private final JwtUtils jwtUtils;
    private final RefreshTokenService refreshTokenService;
    public LoginResponse signIn(LoginRequest request){
        Authentication authentication;
        UserDetailsImpl userDetails;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            userDetails = (UserDetailsImpl) authentication.getPrincipal();
        } catch (BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password", e);
        }

        String jwt =  jwtUtils.generateJwtToken(authentication);
        RefreshToken refreshToken = refreshTokenService.createOrReplaceRefreshToken(userDetails.getUsername());
        UserInfoDto userInfoDto = UserDetailsConvert.ToUserInfoDto(userDetails);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setAccessToken(jwt);
        loginResponse.setRefreshToken(refreshToken.getToken());
        loginResponse.setUser(userInfoDto);
        return loginResponse;
    }
}
